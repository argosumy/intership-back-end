package com.spd.baraholka.advertisements.persistance;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
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
            + "status_change_date) VALUES (?,?,?,?,?,?,?,?,?,?,?) RETURNING id";
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
            statement.setString(7, advertisement.getCity());
            statement.setString(8, advertisement.getStatus().toString());
            statement.setTimestamp(9, Timestamp.valueOf(advertisement.getCreationDate()));
            statement.setTimestamp(10, Timestamp.valueOf(advertisement.getPublicationDate()));
            statement.setTimestamp(11, Timestamp.valueOf(advertisement.getStatusChangeDate()));
            return statement;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }
}
