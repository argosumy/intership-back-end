package com.spd.baraholka.advertisements.persistance;

import com.spd.baraholka.advertisements.services.AdvertisementRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class AdvertisementRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final AdvertisementRowMapper advertisementRowMapper;

    public AdvertisementRepository(NamedParameterJdbcTemplate jdbcTemplate, AdvertisementRowMapper advertisementRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.advertisementRowMapper = advertisementRowMapper;
    }

    public int addAdvertisement(Advertisement advertisement) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource namedParameters = fillInsertParameters(advertisement);
        String insertSQL = createInsertSQL();
        jdbcTemplate.update(insertSQL, namedParameters, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    public int updateAdvertisement(Advertisement advertisement) {
        String updateSQL = createUpdateSQL();
        return jdbcTemplate.update(updateSQL, fillUpdateParameters(advertisement));
    }

    public int updateAdvertisementStatus(int id, AdvertisementStatus status) {
        String updateStatusSQL = createUpdateStatusSQL();
        jdbcTemplate.update(updateStatusSQL, fillUpdateStatusParameters(id, status));
        return id;
    }

    private MapSqlParameterSource fillInsertParameters(Advertisement advertisement) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
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
        namedParameters.addValue("statusChangeDate", Timestamp.valueOf(advertisement.getStatusChangeDate()));
        return namedParameters;
    }

    private Map<String, ? extends Serializable> fillUpdateParameters(Advertisement advertisement) {
        return Map.of("title", advertisement.getTitle(),
                "description", advertisement.getDescription(),
                "category", advertisement.getCategory(),
                "price", advertisement.getPrice(),
                "currency", advertisement.getCurrency().toString(),
                "discountAvailability", advertisement.isDiscountAvailability(),
                "city", advertisement.getCity(),
                "status", advertisement.getStatus().toString(),
                "statusChangeDate", Timestamp.valueOf(advertisement.getStatusChangeDate()),
                "id", advertisement.getAdvertisementId());
    }

    private Map<String, ? extends Comparable<? extends Comparable<?>>> fillUpdateStatusParameters(int id, AdvertisementStatus status) {
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
                + "discount_availability=:discountAvailability,"
                + "city=:city,"
                + "status=:status,"
                + "status_change_date=:statusChangeDate "
                + "WHERE id=:advertisementId";
    }

    private String createInsertSQL() {
        return "INSERT INTO advertisements (title,"
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
                + " VALUES (:title,"
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

    public List<Advertisement> findAdsByTitle(String title) {
        return jdbcTemplate.query(
                "SELECT * FROM advertisements WHERE description LIKE :title",
                Map.of("description", "%" + title + "%"),
                advertisementRowMapper
        );
    }

    public List<Advertisement> findAdsByDescription(String description) {
        return jdbcTemplate.query(
                "SELECT * FROM advertisements WHERE description LIKE :description",
                Map.of("description", "%" + description + "%"),
                advertisementRowMapper
        );
    }
}
