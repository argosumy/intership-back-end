package com.spd.baraholka.user.repository;

import com.spd.baraholka.user.User;
import com.spd.baraholka.user.persistence.UserPersistence;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserRepository implements UserPersistence {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(User user) {
        final String sql = "INSERT INTO users (avatar, first_name, last_name, email, location, position, phone_number) " +
                "VALUES (:avatar, :first_name, :last_name, :email, :location, :phone_number, :position) ";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("avatar", user.getAvatar())
                .addValue("first_name", user.getFirstName())
                .addValue("last_name", user.getLastName())
                .addValue("email", user.getEmail())
                .addValue("location", user.getLocation())
                .addValue("phone_number", user.getPhoneNumber())
                .addValue("position", user.getPosition());

        jdbcTemplate.update(sql, parameters);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jdbcTemplate.queryForObject("SELECT count(*) <> 0 FROM users WHERE LOWER (email) = LOWER (:email)",
                Map.of("email", email), Boolean.class);
    }
}
