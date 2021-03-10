package com.spd.baraholka.advertisements.persistance;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdvertisementRepository {

    private final JdbcTemplate jdbcTemplate;

    public AdvertisementRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
