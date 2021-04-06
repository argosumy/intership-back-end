package com.spd.baraholka.image.persistance.mapper;

import com.spd.baraholka.image.persistance.entity.*;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;

import java.sql.*;

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