package com.spd.baraholka.advertisement.persistance.repositories;

import com.spd.baraholka.advertisement.persistance.PersistenceAdvertisementService;
import com.spd.baraholka.advertisement.persistance.entities.Advertisement;
import com.spd.baraholka.advertisement.persistance.entities.AdvertisementStatus;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@Repository
public class AdvertisementRepository implements PersistenceAdvertisementService {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public AdvertisementRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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

    private MapSqlParameterSource createInsertParameters(Advertisement advertisement) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("ownerId", advertisement.getOwnerId());
        namedParameters.addValue("title", advertisement.getTitle());
        namedParameters.addValue("description", advertisement.getDescription());
        namedParameters.addValue("category", advertisement.getCategory());
        namedParameters.addValue("price", advertisement.getPrice());
        namedParameters.addValue("currency", advertisement.getCurrency().toString());
        namedParameters.addValue("discountAvailability", advertisement.isDiscountAvailability());
        namedParameters.addValue("city", advertisement.getCity());
        namedParameters.addValue("status", advertisement.getStatus().toString());
        namedParameters.addValue("creationDate", Timestamp.valueOf(advertisement.getCreationDate()));
        namedParameters.addValue("publicationDate", Timestamp.valueOf(advertisement.getPublicationDate()));
        namedParameters.addValue("statusChangeDate", Timestamp.valueOf(LocalDateTime.now()));
        return namedParameters;
    }

    private Map<String, ? extends Serializable> createUpdateParameters(Advertisement advertisement) {
        return Map.of("title", advertisement.getTitle(),
                "description", advertisement.getDescription(),
                "category", advertisement.getCategory(),
                "price", advertisement.getPrice(),
                "currency", advertisement.getCurrency().toString(),
                "city", advertisement.getCity(),
                "advertisementId", advertisement.getAdvertisementId());
    }

    private Map<String, ? extends Comparable<? extends Comparable<?>>> createUpdateStatusParameters(int id, AdvertisementStatus status) {
        return Map.of("status", status,
                "statusChangeDate", Timestamp.valueOf(LocalDateTime.now()),
                "advertisementId", id);
    }

    private String createUpdateStatusSQL() {
        return "UPDATE  advertisements " +
                "SET status=:status, status_change_date=:statusChangeDate WHERE id=:advertisementId";
    }

    private String createUpdateSQL() {
        return "UPDATE advertisements SET title=:title,"
                + "description=:description,"
                + "category=:category,"
                + "price=:price,"
                + "currency=:currency,"
                + "city=:city "
                + "WHERE id=:advertisementId";
    }

    private String createInsertSQL() {
        return "INSERT INTO advertisements (user_id, "
                + "title,"
                + "description,"
                + "category,"
                + "price,"
                + "currency,"
                + "discount_availability,"
                + "city,"
                + "status,"
                + "creation_date,"
                + "publication_date,"
                + "status_change_date)"
                + " VALUES (:ownerId, "
                + ":title,"
                + " :description,"
                + " :category,"
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
