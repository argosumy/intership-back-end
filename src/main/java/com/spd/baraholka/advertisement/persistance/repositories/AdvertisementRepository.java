package com.spd.baraholka.advertisement.persistance.repositories;

import com.spd.baraholka.advertisement.persistance.PersistenceAdvertisementService;
import com.spd.baraholka.advertisement.persistance.entities.Advertisement;
import com.spd.baraholka.advertisement.persistance.entities.AdvertisementStatus;
import com.spd.baraholka.advertisement.persistance.mappers.AdvertisementRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Repository
public class AdvertisementRepository implements PersistenceAdvertisementService {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final AdvertisementRowMapper advertisementMapper;

    public AdvertisementRepository(NamedParameterJdbcTemplate jdbcTemplate, AdvertisementRowMapper advertisementRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.advertisementMapper = advertisementRowMapper;
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
        Map<String, ? extends Serializable> updateParameters = createUpdateParameters(advertisement);
        jdbcTemplate.update(updateSQL, updateParameters);
        return advertisement.getAdvertisementId();
    }

    @Override
    public int updateAdvertisementStatus(int id, AdvertisementStatus status) {
        String updateSQL = createUpdateStatusSQL();
        Map<String, ? extends Comparable<? extends Comparable<?>>> updateParameters = createUpdateStatusParameters(id, status);
        jdbcTemplate.update(updateSQL, updateParameters);
        return id;
    }

    @Override
    public Optional<Boolean> isExist(int id) {
        String isExistQuery = "SELECT count(*) <> 0 FROM advertisements WHERE id=:id";
        return Optional.ofNullable(jdbcTemplate.queryForObject(isExistQuery, Map.of("id", id), Boolean.class));
    }

    @Override
    public Optional<Advertisement> selectAdvertisementById(int id) {
        String selectSQL = createSelectSQL();
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(selectSQL, Map.of("id", id), advertisementMapper));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void changeStatusArchivedOnDeleted() {
        String sql = "UPDATE advertisements SET status = :del, status_change_date = now() " +
                "WHERE status = :arch " +
                "AND (now() - status_change_date) < INTERVAL '60 DAY'";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("del", "DELETED");
        params.addValue("arch", "ARCHIVED");

        jdbcTemplate.update(sql, params);
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

    private Map<String, ? extends Serializable> createUpdateParameters(Advertisement advertisement) {
        return Map.of("title", advertisement.getTitle(),
                "status", advertisement.getStatus().toString(),
                "discountAvailability", advertisement.isDiscountAvailability(),
                "publicationDate", advertisement.getPublicationDate(),
                "statusChangeDate", advertisement.getStatusChangeDate(),
                "description", advertisement.getDescription(),
                "price", advertisement.getPrice(),
                "currency", advertisement.getCurrency().toString(),
                "city", advertisement.getCity(),
                "advertisementId", advertisement.getAdvertisementId());
    }

    private String createSelectSQL() {
        return "SELECT id, title, description, price, category, currency, discount_availability, city, status, publication_date, user_id"
                + " FROM advertisements WHERE id=:id";
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

    private Map<String, ? extends Comparable<? extends Comparable<?>>> createUpdateStatusParameters(int id, AdvertisementStatus status) {
        return Map.of("status", status.toString(),
                "statusChangeDate", Timestamp.valueOf(LocalDateTime.now()),
                "advertisementId", id);
    }

    private String createUpdateStatusSQL() {
        return "UPDATE  advertisements SET status=:status, status_change_date=:statusChangeDate WHERE id=:advertisementId";
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
