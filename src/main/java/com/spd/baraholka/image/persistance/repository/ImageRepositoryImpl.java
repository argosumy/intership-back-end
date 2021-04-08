package com.spd.baraholka.image.persistance.repository;

import com.spd.baraholka.image.persistance.entity.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.support.*;
import org.springframework.stereotype.*;

import java.sql.*;
import java.util.*;

@Repository
public class ImageRepositoryImpl implements ImageRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<ImageResource> imageResourceMapper;
    private final RowMapper<Image> imageMapper;

    public ImageRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate, RowMapper<ImageResource> imageResourceMapper, RowMapper<Image> imageMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.imageResourceMapper = imageResourceMapper;
        this.imageMapper = imageMapper;
    }

    @Override
    public Image saveImage(Image image) {
        String sql = "INSERT INTO images(url, is_attached, uploaded_at) " +
                     "VALUES (:imageUrl, :isAttached, :uploadedAt) RETURNING id";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("imageUrl", image.getUrl());
        parameters.addValue("isAttached", image.isAttached());
        parameters.addValue("uploadedAt", Timestamp.valueOf(image.getUploadedAt()));

        jdbcTemplate.update(sql, parameters, keyHolder);

        long id = Objects.requireNonNull(keyHolder.getKey()).longValue();

        image.setId(id);

        return image;
    }

    @Override
    public void save(ImageResource imageResource) {
        String sql = "INSERT INTO advertisements_images(ad_id, image_id, is_primary, position) " +
                     "VALUES(:adId, :imageId, :isPrimary, :position)";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("adId", imageResource.getAdId());
        parameters.addValue("imageId", imageResource.getId());
        parameters.addValue("isPrimary", imageResource.getIsPrimary());
        parameters.addValue("position", imageResource.getPosition());

        jdbcTemplate.update(sql, parameters);
    }

    @Override
    public List<ImageResource> getPrimary(List<Integer> adIds) {
        String sql = "SELECT image_id as id, ad_id, is_primary, position, url " +
                     "FROM advertisements_images " +
                     "LEFT JOIN images ON advertisements_images.image_id = images.id " +
                     "WHERE ad_id IN (:adIds) AND is_primary = :isPrimary";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("adIds", adIds);
        parameters.addValue("isPrimary", true);

        return jdbcTemplate.query(sql, parameters, imageResourceMapper);
    }

    @Override
    public List<ImageResource> getAllByAdId(long adId) {
        String sql = "SELECT images.id as id, ad_id, is_primary, position, url " +
                     "FROM advertisements_images " +
                     "LEFT JOIN images ON advertisements_images.image_id = images.id " +
                     "WHERE ad_id = :adId";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("adId", adId);

        return jdbcTemplate.query(sql, parameters, imageResourceMapper);
    }

    @Override
    public void deleteImage(long imageId) {
        String sql = "DELETE FROM images WHERE id = :imageId";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("imageId", imageId);

        if (jdbcTemplate.update(sql, parameters) != 1) {
            throw new IllegalStateException("Failed to delete the image with id = " + imageId);
        }
    }

    @Override
    public void setAttached(long imageId) {
        String sql = "UPDATE images SET is_attached = :isAttached WHERE id = :imageId";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("imageId", imageId);
        parameters.addValue("isAttached", true);

        jdbcTemplate.update(sql, parameters);
    }

    @Override
    public List<Image> getUnattachedImages() {
        String sql = "SELECT * FROM images WHERE is_attached = false";

        return jdbcTemplate.query(sql, imageMapper);
    }

    @Override
    public Image getImage(long imageId) {
        String sql = "SELECT * FROM images WHERE id = :imageId";

        MapSqlParameterSource parameter = new MapSqlParameterSource();
        parameter.addValue("imageId", imageId);

        return jdbcTemplate.queryForObject(sql, parameter, imageMapper);
    }

    @Override
    public void deleteImageResourcesByAdId(long adId) {
        String sql = "DELETE FROM advertisements_images WHERE ad_id = :adId";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("adId", adId);

        jdbcTemplate.update(sql, parameters);
    }

    @Override
    public boolean existImages(List<Long> imageIds) {
        String sql = "SELECT * FROM images WHERE id IN (:imageIds)";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("imageIds", imageIds);

        List<Image> images = jdbcTemplate.query(sql, parameters, imageMapper);

        return images.size() == imageIds.size();
    }
}
