package com.spd.baraholka.image.persistance.mapper;

import com.spd.baraholka.image.persistance.entity.Image;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class ImageMapper implements RowMapper<Image> {

    @Override
    public Image mapRow(ResultSet rs, int rowNum) throws SQLException {
        long imageId = rs.getInt("id");
        String imageUrl = rs.getString("url");
        boolean isAttached = rs.getBoolean("is_attached");
        LocalDateTime uploadedAt = rs.getTimestamp("uploaded_at").toLocalDateTime();

        Image image = new Image();
        image.setId(imageId);
        image.setUrl(imageUrl);
        image.setAttached(isAttached);
        image.setUploadedAt(uploadedAt);

        return image;
    }
}
