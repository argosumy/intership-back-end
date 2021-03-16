package com.spduniversity.image.repository;

import com.spduniversity.image.ImageResource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class ImageRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<ImageResource> rowMapper;

    public ImageRepository(NamedParameterJdbcTemplate jdbcTemplate, RowMapper<ImageResource> rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    public Optional<ImageResource> getPrimary(long adId) {
        String sql = "DELETE FROM advertisement_images WHERE ad_id = :adId";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("adId", adId);

        ImageResource imageResource = jdbcTemplate.queryForObject(sql, parameters, rowMapper);

        if (Objects.isNull(imageResource)) return Optional.empty();

        return Optional.of(imageResource);
    }

    public void deleteAllByAdId(long adId) {
        String sql = "DELETE FROM advertisement_images WHERE ad_id = :adId";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("adId", adId);

        jdbcTemplate.update(sql, parameters);
    }

    public void saveAll(List<ImageResource> imageResources) {
        String sql = "INSERT INTO advertisement_images(ad_id, is_primary, position_order, image_url) " +
                     "VALUES (:adId, :isPrimary, :positionOrder, :imageUrl)";

        SqlParameterSource[] parameters = SqlParameterSourceUtils.createBatch(imageResources.toArray());

        jdbcTemplate.batchUpdate(sql, parameters);
    }

    public ImageResource save(ImageResource imageResource) {
        String sql = "INSERT INTO advertisement_images(ad_id, is_primary, position_order, image_url) " +
                     "VALUES (:adId, :isPrimary, :positionOrder, :imageUrl) RETURNING id";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("adId", imageResource.getAdId());
        parameters.addValue("imageUrl", imageResource.getImageUrl());
        parameters.addValue("isPrimary", imageResource.getIsPrimary());
        parameters.addValue("positionOrder", imageResource.getPositionOrder());

        jdbcTemplate.update(sql, parameters, keyHolder);

        long id = Objects.requireNonNull(keyHolder.getKey()).longValue();

        imageResource.setId(id);

        return imageResource;
    }

    public List<ImageResource> getAllByAdId(long adId) {
        String sql = "SELECT id, ad_id, is_primary, position_order, image_url FROM advertisement_images WHERE ad_id = :adId";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("adId", adId);

        return jdbcTemplate.query(sql, parameters, rowMapper);
    }

    public Optional<ImageResource> getById(long id) {
        String sql = "SELECT id, ad_id, is_primary, position_order, image_url FROM advertisement_images WHERE id = :id";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);

        ImageResource imageResource = jdbcTemplate.queryForObject(sql, parameters, rowMapper);

        if (Objects.isNull(imageResource)) return Optional.empty();

        return Optional.of(imageResource);
    }

    public void delete(long id) {
        String sql = "DELETE FROM advertisement_images WHERE id = :id";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);

        if (jdbcTemplate.update(sql, parameters) != 1)
            throw new IllegalStateException("Failed to delete the image with id = " + id);
    }
}
