package com.spd.baraholka.wishlist.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishlistRepositoryImpl implements WishlistRepository{
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final WishlistMapper wishlistMapper;

    @Autowired
    public WishlistRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate, WishlistMapper wishlistMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.wishlistMapper = wishlistMapper;
    }

    @Override
    public void save(int userId, int advertisementsId) {
        final String sql = "INSERT INTO wish_list (user_id, advertisements_id) VALUES (:userId, :advertisementsId)";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("user_id", userId)
                .addValue("advertisements_id", advertisementsId);

        jdbcTemplate.update(sql, parameters);
    }

    @Override
    public List<Integer> read(int userId) {
        String sql = "SELECT advertisements_id FROM wish_list WHERE user_id = :userId";

        return jdbcTemplate.query(sql, new MapSqlParameterSource("user_id", userId), wishlistMapper);
    }

    @Override
    public void delete(int userId, int advertisementsId) {
        String sql = "DELETE FROM wish_list WHERE user_id = :userId AND advertisements_id = :advertisementsId";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("user_id", userId)
                .addValue("advertisements_id", advertisementsId);

        jdbcTemplate.update(sql, parameters);
    }
}
