package com.spd.baraholka.advertisements.persistance;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Repository
public class AdvertisementRepository {

    private static final String INSERT_ADVERTISEMENT = "INSERT INTO advertisements (user_id,"
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
            + "status_change_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?) RETURNING id";
    private static final String UPDATE_ADVERTISEMENT = "UPDATE advertisements SET title=?,"
            + "description=?,"
            + "category=?,"
            + "price=?,"
            + "currency=?,"
            + "discount_availability=?,"
            + "city=?,"
            + "status=?,"
            + "status_change_date=?"
            + "WHERE id=?";
    private static final String UPDATE_ADVERTISEMENT_STATUS = "UPDATE  advertisements SET status=?, status_change_date=? WHERE id=?";
    private final JdbcTemplate jdbcTemplate;

    public AdvertisementRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addAdvertisement(Advertisement advertisement) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement(INSERT_ADVERTISEMENT, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, advertisement.getOwnerId());
            statement.setString(2, advertisement.getTitle());
            statement.setString(3, advertisement.getDescription());
            statement.setString(4, advertisement.getCategory());
            statement.setDouble(5, advertisement.getPrice());
            statement.setString(6, advertisement.getCurrency().toString());
            statement.setBoolean(7, advertisement.isDiscountAvailability());
            statement.setString(8, advertisement.getCity());
            statement.setString(9, advertisement.getStatus().toString());
            statement.setTimestamp(10, Timestamp.valueOf(advertisement.getCreationDate()));
            statement.setTimestamp(11, Timestamp.valueOf(advertisement.getPublicationDate()));
            statement.setTimestamp(12, Timestamp.valueOf(advertisement.getStatusChangeDate()));
            return statement;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    public int updateAdvertisement(Advertisement advertisement) {
        jdbcTemplate.update(UPDATE_ADVERTISEMENT,
                advertisement.getTitle(),
                advertisement.getDescription(),
                advertisement.getCategory(),
                advertisement.getPrice(),
                advertisement.getCurrency().toString(),
                advertisement.isDiscountAvailability(),
                advertisement.getCity(),
                advertisement.getStatus().toString(),
                Timestamp.valueOf(advertisement.getStatusChangeDate()),
                advertisement.getAdvertisementId());
        return advertisement.getAdvertisementId();
    }

    public int updateAdvertisementStatus(int id, AdvertisementStatus status) {
        jdbcTemplate.update(UPDATE_ADVERTISEMENT_STATUS, status, Timestamp.valueOf(LocalDateTime.now()), id);
        return id;
    }
}
