package com.spd.baraholka.image.repository;

import com.spd.baraholka.image.ImageResource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ImageResourceMapper implements RowMapper<ImageResource> {

    @Override
    public ImageResource mapRow(ResultSet rs, int rowNum) throws SQLException {

        long id = rs.getLong("id");
        long adId = rs.getLong("ad_id");
        boolean isPrimary = rs.getBoolean("is_primary");
        int positionOrder = rs.getInt("position");
        String imageUrl = rs.getString("url");

        return new ImageResource(id, adId, isPrimary, positionOrder, imageUrl);
    }
}
