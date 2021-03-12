package com.spd.baraholka.advertisements.repositories;


import com.spd.baraholka.advertisements.persistance.Advertisement;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdvertisementRepository implements AdvertisementPersistence {

    private final JdbcTemplate jdbcTemplate;

    public AdvertisementRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Advertisement> findAdsByTitle(String title) {
        return null;
    }

    @Override
    public List<Advertisement> findAdsByDescription(String description) {
        return null;
//        return jdbcTemplate.query(
//                "SELECT * FROM advertisements WHERE description LIKE ?",
//                new AdvertisementMapper(),
//                "%" + description + "%"
//        );
    }
}
