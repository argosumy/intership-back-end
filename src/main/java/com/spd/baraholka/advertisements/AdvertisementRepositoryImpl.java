package com.spd.baraholka.advertisements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdvertisementRepositoryImpl implements AdvertisementRepository{
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public AdvertisementRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void changeStatusArchivedOnDeleted() {
        String sql = "UPDATE advertisements SET status = :del, status_change_date = now() " +
                "WHERE status = :arch " +
                "AND (now() - status_change_date) < INTERVAL '60 DAY' ";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("del","DELETED");
        params.addValue("arch","ARCHIVED");

        jdbcTemplate.update(sql, params);
    }
}
