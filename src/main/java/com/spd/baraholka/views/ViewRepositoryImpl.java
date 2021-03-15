package com.spd.baraholka.views;

import com.spd.baraholka.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ViewRepositoryImpl implements ViewRepository{
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final ViewMapper viewMapper;

    @Autowired
    public ViewRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate, ViewMapper viewMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.viewMapper = viewMapper;
    }

    @Override
    public List<View> read(int userId) {
        String sql = "SELECT id, user_id, advertisements_id, viewed_at FROM history_of_views WHERE user_id = :userId";
        List<View> list;

        try {
            list = jdbcTemplate.query(sql, new MapSqlParameterSource("user_id", userId), viewMapper);
        } catch (EmptyResultDataAccessException exception) {
            throw new NotFoundException("Views with id = " + userId + "don`t found");
        }

        return list;
    }

    @Override
    public void save(View view) {
        final String sql = "INSERT INTO history_of_views(user_id, advertisements_id, viewed_at) " +
                "VALUES (:userId, :advertisementsId, :viewed_at)";

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("user_id", view.getUserId())
                .addValue("advertisements_id", view.getAdvertisementId())
                .addValue("viewed_at", view.getViewedAt());

        jdbcTemplate.update(sql, parameters);
    }
}
