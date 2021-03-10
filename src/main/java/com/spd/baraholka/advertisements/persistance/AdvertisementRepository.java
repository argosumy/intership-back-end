package com.spd.baraholka.advertisements.persistance;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Objects;

@Repository
public class AdvertisementRepository {

    private static final String INSERT_ADVERTISEMENT = "INSERT INTO advertisements (user_id,"
            + "title,"
            + "description,"
            + "category,"
            + "price,"
            + "currency,"
            + "city,"
            + "status,"
            + "creation_date,"
            + "publication_date,"
            + "status_change_date) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    private final JdbcTemplate jdbcTemplate;

    public AdvertisementRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addAdvertisement(Advertisement advertisement) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            final PreparedStatement statement = con.prepareStatement(INSERT_ADVERTISEMENT);
            statement.setInt(1, advertisement.getOwnerId());
            statement.setString(2, advertisement.getTitle());
            statement.setString(2, advertisement.getDescription());
            statement.setString(3, advertisement.getCategory());
            statement.setDouble(4, advertisement.getPrice());
            statement.setString(5, advertisement.getCurrency().toString());
            statement.setString(6, advertisement.getCity());
            statement.setString(7, advertisement.getStatus().toString());
            statement.setTimestamp(8, Timestamp.valueOf(advertisement.getCreationDate()));
            statement.setTimestamp(9, Timestamp.valueOf(advertisement.getPublicationDate()));
            statement.setTimestamp(10, Timestamp.valueOf(advertisement.getStatusChangeDate()));
            return statement;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }
}
