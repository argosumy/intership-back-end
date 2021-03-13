package com.spd.baraholka.advertisements.services;

import com.spd.baraholka.advertisements.persistance.Advertisement;
import com.spd.baraholka.advertisements.persistance.AdvertisementStatus;
import com.spd.baraholka.advertisements.persistance.CurrencyType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AdvertisementRowMapper implements RowMapper<Advertisement> {

    @Override
    public Advertisement mapRow(ResultSet rs, int rowNum) throws SQLException {
        Advertisement advertisement = new Advertisement();
        advertisement.setAdvertisementId(rs.getInt("id"));
        advertisement.setOwnerId(rs.getInt("user_id"));
        advertisement.setTitle(rs.getString("title"));
        advertisement.setDescription(rs.getString("description"));
        advertisement.setCategory(rs.getString("category"));
        advertisement.setPrice(rs.getDouble("price"));
        advertisement.setCurrency(CurrencyType.valueOf(rs.getString("currency")));
        advertisement.setDiscountAvailability(rs.getBoolean("discount_availability"));
        advertisement.setCity(rs.getString("city"));
        advertisement.setStatus(AdvertisementStatus.valueOf(rs.getString("status")));
        advertisement.setCreationDate(rs.getTimestamp("creation_date").toLocalDateTime());
        advertisement.setPublicationDate(rs.getTimestamp("publication_date").toLocalDateTime());
        advertisement.setStatusChangeDate(rs.getTimestamp("status_change_date").toLocalDateTime());
        return advertisement;
    }
}