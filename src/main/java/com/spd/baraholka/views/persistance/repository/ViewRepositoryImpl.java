package com.spd.baraholka.views.persistance.repository;

import com.spd.baraholka.views.persistance.entities.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ViewRepositoryImpl implements ViewRepository {

    private static final String SELECT_COUNT_OF_VIEWS_SQL = "SELECT count(DISTINCT user_id) FROM history_of_views WHERE advertisements_id =:advertisementId";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public ViewRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<View> read(int userId) {
        String sql = "SELECT id, user_id, advertisements_id, viewed_at FROM history_of_views WHERE user_id = :user_id";

        return jdbcTemplate.query(sql, new MapSqlParameterSource("user_id", userId), rs -> {
            List<View> list = new ArrayList<>();
            while (rs.next()) {
                View view = new View(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("advertisements_id"),
                        rs.getTimestamp("viewed_at").toLocalDateTime()
                );
                list.add(view);
            }
            return list;
        });
    }

    @Override
    public void save(int userId, int advertisementsId) {
        final String sql = "INSERT INTO history_of_views(user_id, advertisements_id) " +
                "VALUES (:user_id, :advertisements_id) ";

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("user_id", userId)
                .addValue("advertisements_id", advertisementsId);

        jdbcTemplate.update(sql, parameters);
    }

    @Override
    public Integer getCountOfViews(int advertisementId) {
        return jdbcTemplate.queryForObject(SELECT_COUNT_OF_VIEWS_SQL, Map.of("advertisementId", advertisementId), Integer.class);
    }
}
