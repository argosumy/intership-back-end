package com.spd.baraholka.wishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class WishlistRepositoryImpl {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(User user) {
        final String sql = "INSERT INTO users (avatar, first_name, last_name, email " +
                "VALUES (:avatar, :first_name, :last_name, :email) ";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("avatar", user.getAvatar())
                .addValue("first_name", user.getFirstName())
                .addValue("last_name", user.getLastName())
                .addValue("email", user.getEmail());

        jdbcTemplate.update(sql, parameters);
    }
}
}
