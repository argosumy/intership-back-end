package com.spd.baraholka.promotion.persistance.repositiries;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class PromotionAdvertisementRepository {
    private final JdbcTemplate jdbcTemplate;

    public PromotionAdvertisementRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void updatePromotionDate(int idAdvertisement) {
        LocalDateTime realDate = LocalDateTime.now();
        jdbcTemplate.update("UPDATE advertisements SET promoted_at = ? WHERE id = ?", realDate, idAdvertisement);
    }
}
