package com.spd.baraholka.advertisements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class AdvertisementRepositoryImpl implements AdvertisementRepository{
    private static final int TIME_OF_STORAGE_ARCHIVED_ADVERTISEMENT = 5184000;
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final IdAdvertisementMapper idAdvertisementMapper;

    @Autowired
    public AdvertisementRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate,
                                       IdAdvertisementMapper idAdvertisementMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.idAdvertisementMapper = idAdvertisementMapper;
    }

    @Override
    public List<Integer> getArchivedAdvertisementForDeleting() {
//        String sql = "SELECT id FROM advertisements " +
//        "WHERE status = 'ARCHIVED' " +
//        "AND (now() - status_change_date) > :time_storage::TIMESTAMP " +
//        "AT TIME ZONE 'UTC' AT TIME ZONE 'PST'";

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //timestamp = timestamp - 5184000;
        String sql = "SELECT id FROM advertisements " +
                "WHERE status = 'ARCHIVED' " +
                "AND (now() - status_change_date) > :time_storage::TIMESTAMP " +
                "AT TIME ZONE 'UTC' AT TIME ZONE 'PST'";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("time_storage", TIME_OF_STORAGE_ARCHIVED_ADVERTISEMENT);

        return jdbcTemplate.query(sql, params, idAdvertisementMapper);
    }

    @Override
    public void updateArchivedStatusToDeleted(int id) {
        String sql = "UPDATE advertisements SET status = 'DELETED' WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        jdbcTemplate.update(sql, params);
    }
}
