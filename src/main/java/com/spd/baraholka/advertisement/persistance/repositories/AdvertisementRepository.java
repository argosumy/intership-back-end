package com.spd.baraholka.advertisement.persistance.repositories;

import com.spd.baraholka.advertisement.controller.dto.FilterDTO;
import com.spd.baraholka.advertisement.persistance.PersistenceAdvertisementService;
import com.spd.baraholka.advertisement.persistance.entities.Advertisement;
import com.spd.baraholka.advertisement.persistance.entities.AdvertisementStatus;
import com.spd.baraholka.advertisement.persistance.mappers.AdvertisementRowMapper;
import com.spd.baraholka.characteristic.persistance.entities.Category;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class AdvertisementRepository implements PersistenceAdvertisementService {

    private static final String CATEGORY_CONDITION = " AND a.category = :category";
    private static final String PRICE_RANGE_CONDITION = " AND a.price BETWEEN :min AND :max";
    private static final String MAX_PRICE_CONDITION = " AND a.price <= :max";
    private static final String MIN_PRICE_CONDITION = " AND a.price >= :min";
    private static final String CITY_CONDITION = " AND a.city = :city";
    private static final String STATUS_CONDITION = " AND a.status = :status";
    private static final String CHARACTERISTICS_CONDITION = " AND c.characteristics_name = :characteristics_name AND c.characteristics_value = :characteristics_value " +
            "AND is_approved = TRUE AND is_deleted = FALSE";

    private static final String UPDATE_ADVERTISEMENTS_STATUS_SQL =
            "UPDATE  advertisements SET status=:status, status_change_date=:statusChangeDate WHERE id=:advertisementId";
    private static final String SELECT_ADVERTISEMENT_BY_ID_SQL =
            "SELECT id, title, description, price, category, currency, discount_availability, city, status, publication_date, user_id FROM advertisements WHERE id=:id";
    private static final String EXIST_BY_ID_SQL = "SELECT count(*) <> 0 FROM advertisements WHERE id=:id";
    private static final String ADVERTISEMENT_SOFT_DELETE_SQL = "UPDATE advertisements SET status = :del, status_change_date = now() " +
            "WHERE status = :arch " +
            "AND (now() - status_change_date) < INTERVAL '60 DAY'";
    private static final String SELECT_ADS_BY_USER_AND_STATUS_SQL = "SELECT * FROM advertisements a WHERE a.status = :status AND a.user_id = :user_id";
    private static final String SELECT_PUBLISHED_ADS_SQL = "SELECT * FROM advertisements a " +
            "WHERE (a.status = 'ACTIVE' OR (a.status = 'DELAYED_PUBLICATION' AND a.publication_date::text <= :publicationDate))";
    private static final String SELECT_WISHLIST_ADS_BY_USER_SQL = "SELECT * FROM advertisements a INNER JOIN wishlist w ON a.id = w.ad_id WHERE w.user_id = :user_id";
    private static final String SELECT_PUBLISHED_ADS_BY_CATEGORY_SQL = SELECT_PUBLISHED_ADS_SQL + CATEGORY_CONDITION;
    private static final String SELECT_PUBLISHED_ADS_BY_CHARACTERISTICS_JOIN_SQL =
            "SELECT * FROM advertisements a INNER JOIN characteristics c ON a.id = c.advertisement_id";
    private static final String SELECT_PUBLISHED_ADS_BY_CHARACTERISTICS_SQL = SELECT_PUBLISHED_ADS_BY_CHARACTERISTICS_JOIN_SQL + CHARACTERISTICS_CONDITION;
    private static final String SELECT_PUBLISHED_ADS_BY_PRICE_SQL = SELECT_PUBLISHED_ADS_SQL + PRICE_RANGE_CONDITION;
    private static final String SELECT_PUBLISHED_ADS_BY_CATEGORY_AND_PRICE_SQL = SELECT_PUBLISHED_ADS_SQL + CATEGORY_CONDITION + PRICE_RANGE_CONDITION;

    private final NamedParameterJdbcTemplate jdbcTemplate;
    public static final String STATUS_PARAMETER = "status";
    public static final String STATUS_CHANGE_DATE_PARAMETER = "statusChangeDate";
    public static final String PUBLICATION_DATE_PARAMETER = "publicationDate";
    private final AdvertisementRowMapper advertisementMapper;

    public AdvertisementRepository(NamedParameterJdbcTemplate jdbcTemplate, AdvertisementRowMapper advertisementRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.advertisementMapper = advertisementRowMapper;
    }

    @Override
    public void updatePromotionDate(int idAdvertisement) {
        LocalDateTime realDate = LocalDateTime.now();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("date", realDate)
                .addValue("id", idAdvertisement);
        jdbcTemplate.update("UPDATE advertisements SET promoted_at = :date WHERE id = :id", sqlParameterSource);
    }

    @Override
    public int insertAdvertisement(Advertisement advertisement) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        String insertSQL = createInsertSQL();
        MapSqlParameterSource insertParameters = createInsertParameters(advertisement);
        jdbcTemplate.update(insertSQL, insertParameters, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public int updateAdvertisement(Advertisement advertisement) {
        String updateSQL = createUpdateSQL();
        MapSqlParameterSource updateParameters = createUpdateParameters(advertisement);
        jdbcTemplate.update(updateSQL, updateParameters);
        return advertisement.getAdvertisementId();
    }

    @Override
    public int updateAdvertisementStatus(int id, AdvertisementStatus status) {
        MapSqlParameterSource updateParameters = createUpdateStatusParameters(id, status);
        jdbcTemplate.update(UPDATE_ADVERTISEMENTS_STATUS_SQL, updateParameters);
        return id;
    }

    @Override
    public Optional<Boolean> isExist(int id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(EXIST_BY_ID_SQL, Map.of("id", id), Boolean.class));
    }

    @Override
    public Optional<Advertisement> selectAdvertisementById(int id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SELECT_ADVERTISEMENT_BY_ID_SQL, Map.of("id", id), advertisementMapper));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void changeStatusArchivedOnDeleted() {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("del", "DELETED");
        params.addValue("arch", "ARCHIVED");
        jdbcTemplate.update(ADVERTISEMENT_SOFT_DELETE_SQL, params);
    }

    @Override
    public List<Advertisement> getAllActive() { // TODO: rename to getAllPublished()
        return jdbcTemplate.query(SELECT_PUBLISHED_ADS_SQL,
                Map.of(
//                        "active", AdvertisementStatus.ACTIVE.name(),
//                        "delayed", AdvertisementStatus.DELAYED_PUBLICATION.name(),
                        PUBLICATION_DATE_PARAMETER, LocalDateTime.now()
                ),
                advertisementMapper
        );
    }

    @Override
    public List<Advertisement> getPublishedFilteredAds(FilterDTO filterDTO) {
        String sql = SELECT_PUBLISHED_ADS_SQL;
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(PUBLICATION_DATE_PARAMETER, LocalDateTime.now().toString());
        Objects.requireNonNull(filterDTO);
        if ((filterDTO.getCharacteristics() != null) && !filterDTO.getCharacteristics().isEmpty()) {
            sql = SELECT_PUBLISHED_ADS_BY_CHARACTERISTICS_SQL;
            filterDTO.getCharacteristics().forEach((k, v) -> {
                // TODO
                parameters.put("characteristics_name", k);
                parameters.put("characteristics_value", v);
            });
        }
        if (filterDTO.getCategory() != null) {
            sql += CATEGORY_CONDITION;
            parameters.put("category", filterDTO.getCategory().name());
        }
        if (filterDTO.getCity() != null) {
            sql += CITY_CONDITION;
            parameters.put("city", filterDTO.getCity());
        }
        if (filterDTO.getMaxPrice() != null) {
            parameters.put("max", filterDTO.getMaxPrice());
            if (filterDTO.getMinPrice() != null) {
                sql += PRICE_RANGE_CONDITION;
                parameters.put("min", filterDTO.getMinPrice());
            } else {
                sql += MAX_PRICE_CONDITION;
            }
        } else {
            if (filterDTO.getMinPrice() != null) {
                sql += MIN_PRICE_CONDITION;
                parameters.put("min", filterDTO.getMinPrice());
            }
        }
        return jdbcTemplate.query(sql, parameters, advertisementMapper);
    }

    @Override
    public List<Advertisement> getAllPublishedByCategory(Category category) {
        return jdbcTemplate.query(SELECT_PUBLISHED_ADS_BY_CATEGORY_SQL,
                Map.of(
//                        "active", AdvertisementStatus.ACTIVE.name(),
//                        "delayed", AdvertisementStatus.DELAYED_PUBLICATION.name(),
                        "category", category.name(),
                        PUBLICATION_DATE_PARAMETER, LocalDateTime.now()
                ),
                advertisementMapper
        );
    }

    @Override
    public List<Advertisement> getAllPublishedByPrice(double min, double max) {
        return jdbcTemplate.query(SELECT_PUBLISHED_ADS_BY_CATEGORY_SQL,
                Map.of(
//                        "active", AdvertisementStatus.ACTIVE.name(),
//                        "delayed", AdvertisementStatus.DELAYED_PUBLICATION.name(),
                        "min", min,
                        "max", max,
                        PUBLICATION_DATE_PARAMETER, LocalDateTime.now()
                ),
                advertisementMapper
        );
    }

    @Override
    public List<Advertisement> getAllPublishedByCategoryAndPrice(Category category, double min, double max) {
        return jdbcTemplate.query(SELECT_PUBLISHED_ADS_BY_CATEGORY_SQL,
                Map.of(
//                        "active", AdvertisementStatus.ACTIVE.name(),
//                        "delayed", AdvertisementStatus.DELAYED_PUBLICATION.name(),
                        "category", category.name(),
                        "min", min,
                        "max", max,
                        PUBLICATION_DATE_PARAMETER, LocalDateTime.now()
                ),
                advertisementMapper
        );
    }

    @Override
    public List<Advertisement> getAllAdsWithStatusByUser(int userId, AdvertisementStatus status) {
        return jdbcTemplate.query(SELECT_ADS_BY_USER_AND_STATUS_SQL,
                Map.of(STATUS_PARAMETER, status.name(),
                        "user_id", userId
                ),
                advertisementMapper
        );
    }

    @Override
    public Optional<Advertisement> findDraftAdById(int id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(
                    "SELECT * FROM advertisements WHERE id=:id AND status=:status",
                    Map.of("id", id,
                            STATUS_PARAMETER, "DRAFT"
                    ),
                    advertisementMapper
            ));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    private MapSqlParameterSource createInsertParameters(Advertisement advertisement) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("ownerId", advertisement.getOwnerId());
        namedParameters.addValue("category", advertisement.getCategory());
        namedParameters.addValue("title", advertisement.getTitle());
        namedParameters.addValue("description", advertisement.getDescription());
        namedParameters.addValue("price", advertisement.getPrice());
        namedParameters.addValue("currency", advertisement.getCurrency().toString());
        namedParameters.addValue("discountAvailability", advertisement.isDiscountAvailability());
        namedParameters.addValue("city", advertisement.getCity());
        namedParameters.addValue(STATUS_PARAMETER, advertisement.getStatus().toString());
        namedParameters.addValue("creationDate", Timestamp.valueOf(advertisement.getCreationDate()));
        namedParameters.addValue(PUBLICATION_DATE_PARAMETER, Timestamp.valueOf(advertisement.getPublicationDate()));
        namedParameters.addValue(STATUS_CHANGE_DATE_PARAMETER, Timestamp.valueOf(advertisement.getStatusChangeDate()));
        return namedParameters;
    }

    private MapSqlParameterSource createUpdateParameters(Advertisement advertisement) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("title", advertisement.getTitle());
        namedParameters.addValue("status", advertisement.getStatus().toString());
        namedParameters.addValue("discountAvailability", advertisement.isDiscountAvailability());
        namedParameters.addValue("publicationDate", advertisement.getPublicationDate());
        namedParameters.addValue("statusChangeDate", advertisement.getStatusChangeDate());
        namedParameters.addValue("description", advertisement.getDescription());
        namedParameters.addValue("price", advertisement.getPrice());
        namedParameters.addValue("currency", advertisement.getCurrency().toString());
        namedParameters.addValue("city", advertisement.getCity());
        namedParameters.addValue("advertisementId", advertisement.getAdvertisementId());
        return namedParameters;
    }

    private MapSqlParameterSource createUpdateStatusParameters(int id, AdvertisementStatus status) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("status", status.toString());
        namedParameters.addValue("statusChangeDate", Timestamp.valueOf(LocalDateTime.now()));
        namedParameters.addValue("advertisementId", id);
        return namedParameters;
    }

    private String createUpdateSQL() {
        return "UPDATE advertisements SET title=:title, "
                + "status=:status, "
                + "discount_availability=:discountAvailability, "
                + "publication_date=:publicationDate, "
                + "status_change_date=:statusChangeDate,"
                + "description=:description,"
                + "price=:price,"
                + "currency=:currency,"
                + "city=:city "
                + "WHERE id=:advertisementId";
    }

    private String createInsertSQL() {
        return "INSERT INTO advertisements (user_id, "
                + "category,"
                + "title,"
                + "description,"
                + "price,"
                + "currency,"
                + "discount_availability,"
                + "city,"
                + "status,"
                + "creation_date,"
                + "publication_date,"
                + "status_change_date)"
                + " VALUES (:ownerId, :category,"
                + ":title,"
                + " :description,"
                + " :price,"
                + " :currency,"
                + " :discountAvailability,"
                + " :city,"
                + " :status,"
                + " :creationDate,"
                + " :publicationDate,"
                + " :statusChangeDate)"
                + " RETURNING id";
    }
}