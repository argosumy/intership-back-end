package com.spd.baraholka.advertisement.persistance.repositories;

import com.spd.baraholka.advertisement.persistance.PersistenceAdvertisementService;
import com.spd.baraholka.advertisement.persistance.entities.Advertisement;
import com.spd.baraholka.advertisement.persistance.entities.AdvertisementStatus;
import com.spd.baraholka.advertisement.persistance.mappers.AdvertisementRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Repository
public class AdvertisementRepository implements PersistenceAdvertisementService {

    private static final String UPDATE_ADVERTISEMENTS_STATUS_SQL =
            "UPDATE  advertisements SET status=:status, status_change_date=:statusChangeDate WHERE id=:advertisementId";
    private static final String SELECT_ADVERTISEMENT_BY_ID_SQL =
            "SELECT id, title, description, price, category, currency, discount_availability, city, status, publication_date, user_id FROM advertisements WHERE id=:id";
    private static final String EXIST_BY_ID_SQL = "SELECT count(*) <> 0 FROM advertisements WHERE id=:id";
    private static final String ADVERTISEMENT_SOFT_DELETE_SQL = "UPDATE advertisements SET status = :del, status_change_date = now() " +
            "WHERE status = :arch " +
            "AND (now() - status_change_date) < INTERVAL '60 DAY'";
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
    public List<Advertisement> getAllActive() {
        return jdbcTemplate.query(
                "SELECT * FROM advertisements a WHERE a.status=:active OR " +
                        "(a.status=:draft AND a.publication_date<=:publicationDate)",
                Map.of("active", "ACTIVE",
                        "draft", "DRAFT",
                        PUBLICATION_DATE_PARAMETER, LocalDateTime.now()
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
        namedParameters.addValue("status", advertisement.getStatus().toString());
        namedParameters.addValue("creationDate", Timestamp.valueOf(advertisement.getCreationDate()));
        namedParameters.addValue("publicationDate", Timestamp.valueOf(advertisement.getPublicationDate()));
        namedParameters.addValue("statusChangeDate", Timestamp.valueOf(advertisement.getStatusChangeDate()));
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
