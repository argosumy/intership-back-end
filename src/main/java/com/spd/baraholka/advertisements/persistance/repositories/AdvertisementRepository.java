package com.spd.baraholka.advertisements.persistance.repositories;

import com.spd.baraholka.advertisements.persistance.PersistenceAdvertisementService;
import com.spd.baraholka.advertisements.persistance.entities.Advertisement;
import com.spd.baraholka.advertisements.persistance.entities.AdvertisementStatus;
import com.spd.baraholka.advertisements.controller.mappers.AdvertisementRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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

    public static final String STATUS_PARAMETER = "status";
    public static final String STATUS_CHANGE_DATE_PARAMETER = "statusChangeDate";
    public static final String PUBLICATION_DATE_PARAMETER = "publicationDate";
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
        namedParameters.addValue(STATUS_PARAMETER, advertisement.getStatus().toString());
        namedParameters.addValue("creationDate", Timestamp.valueOf(advertisement.getCreationDate()));
        namedParameters.addValue(PUBLICATION_DATE_PARAMETER, Timestamp.valueOf(advertisement.getPublicationDate()));
        namedParameters.addValue(STATUS_CHANGE_DATE_PARAMETER, Timestamp.valueOf(advertisement.getStatusChangeDate()));
        return namedParameters;
    }

    private Map<String, Object> fillUpdateParameters(Advertisement advertisement) {
        return Map.ofEntries(
                Map.entry("title", advertisement.getTitle()),
                Map.entry("description", advertisement.getDescription()),
                Map.entry("category", advertisement.getCategory()),
                Map.entry("price", advertisement.getPrice()),
                Map.entry("currency", advertisement.getCurrency().toString()),
                Map.entry("discountAvailability", advertisement.isDiscountAvailability()),
                Map.entry("city", advertisement.getCity()),
                Map.entry(STATUS_PARAMETER, advertisement.getStatus().toString()),
                Map.entry(STATUS_CHANGE_DATE_PARAMETER, Timestamp.valueOf(advertisement.getStatusChangeDate())),
                Map.entry(PUBLICATION_DATE_PARAMETER, Timestamp.valueOf(advertisement.getPublicationDate())),
                Map.entry("advertisementId", advertisement.getAdvertisementId())
        );
    }

    private Map<String, ? extends Comparable<? extends Comparable<?>>> fillUpdateStatusParameters(int id, AdvertisementStatus status) {
        return Map.of(STATUS_PARAMETER, status,
                STATUS_CHANGE_DATE_PARAMETER, Timestamp.valueOf(LocalDateTime.now()),
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
                + "status_change_date=:statusChangeDate,"
                + "publication_date=:publicationDate "
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

    public List<Advertisement> getAllActive() {
        return jdbcTemplate.query(
                "SELECT * FROM advertisements a WHERE a.status=:active OR " +
                        "(a.status=:draft AND a.publication_date<=:publicationDate)",
                Map.of("active", "ACTIVE",
                        "draft", "DRAFT",
                        PUBLICATION_DATE_PARAMETER, LocalDateTime.now()
                ),
                advertisementRowMapper
        );
    }

    public Optional<Advertisement> findDraftAdById(int id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(
                    "SELECT * FROM advertisements WHERE id=:id AND status=:status",
                    Map.of("id", id,
                            STATUS_PARAMETER, "DRAFT"
                    ),
                    advertisementRowMapper
            ));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }
}
