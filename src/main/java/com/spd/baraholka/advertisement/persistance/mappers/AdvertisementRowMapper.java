package com.spd.baraholka.advertisement.persistance.mappers;

import com.spd.baraholka.advertisement.persistance.entities.Advertisement;
import com.spd.baraholka.advertisement.persistance.entities.AdvertisementStatus;
import com.spd.baraholka.advertisement.persistance.entities.CurrencyType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class AdvertisementRowMapper implements RowMapper<Advertisement> {

    @Override
    public Advertisement mapRow(ResultSet rs, int rowNum) throws SQLException {
        Advertisement advertisement = new Advertisement();
        advertisement.setAdvertisementId(rs.getInt("id"));
        advertisement.setOwnerId(rs.getInt("user_id"));
        advertisement.setTitle(rs.getString("title"));
        advertisement.setDescription(rs.getString("description"));
        advertisement.setPrice(rs.getDouble("price"));
        advertisement.setCategory(rs.getString("category"));
        setCurrency(rs, advertisement);
        advertisement.setDiscountAvailability(rs.getBoolean("discount_availability"));
        advertisement.setCity(rs.getString("city"));
        setStatus(rs, advertisement);
        setPublicationDate(rs, advertisement);
        return advertisement;
    }

    private void setCurrency(ResultSet resultSet, Advertisement advertisement) throws SQLException {
        String currency = resultSet.getString("currency");
        if (currency != null) {
            CurrencyType currencyType = CurrencyType.valueOf(currency);
            advertisement.setCurrency(currencyType);
        }
    }

    private void setStatus(ResultSet resultSet, Advertisement advertisement) throws SQLException {
        String status = resultSet.getString("status");
        if (status != null) {
            AdvertisementStatus advertisementStatus = AdvertisementStatus.valueOf(status);
            advertisement.setStatus(advertisementStatus);
        }
    }

    private void setPublicationDate(ResultSet resultSet, Advertisement advertisement) throws SQLException {
        Timestamp publicationTimeStamp = resultSet.getTimestamp("publication_date");
        if (publicationTimeStamp != null) {
            LocalDateTime publicationDate = publicationTimeStamp.toLocalDateTime();
            advertisement.setPublicationDate(publicationDate);
        }
    }
}
