package com.spduniversity.image.repository;

import com.spduniversity.image.ImageResource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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

    public long saveImageUrl(String imageUrl) {
        String sql = "INSERT INTO images(url) VALUES (:imageUrl) RETURNING id";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("imageUrl", imageUrl);

        jdbcTemplate.update(sql, parameters, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

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

    public List<ImageResource> getPrimary(List<Long> adIds) {
        String sql = "SELECT image_id as id, ad_id, is_primary, position, url " +
                     "FROM advertisements_images " +
                     "LEFT JOIN images ON advertisements_images.image_id = images.id " +
                     "WHERE ad_id IN (:adIds) AND is_primary = :isPrimary";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("adIds", adIds);
        parameters.addValue("isPrimary", true);

        return jdbcTemplate.query(sql, parameters, rowMapper);
    }

    public List<ImageResource> getAllByAdId(long adId) {
        String sql = "SELECT images.id as id, ad_id, is_primary, position, url " +
                     "FROM advertisements_images " +
                     "LEFT JOIN images ON advertisements_images.image_id = images.id";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("adId", adId);

        return jdbcTemplate.query(sql, parameters, rowMapper);
    }

    public Optional<ImageResource> getImageById(long imageId) {
        String sql = "SELECT images.id as id, ad_id, is_primary, position, url FROM advertisements_images " +
                     "LEFT JOIN images ON advertisements_images.image_id = images.id " +
                     "WHERE image_id = :imageId";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("imageId", imageId);

        ImageResource imageResource = jdbcTemplate.queryForObject(sql, parameters, rowMapper);

        if (Objects.isNull(imageResource)) return Optional.empty();

        return Optional.of(imageResource);
    }

    public void deleteImage(long imageId) {
        String sql = "DELETE FROM images WHERE id = :imageId";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("imageId", imageId);

        if (jdbcTemplate.update(sql, parameters) != 1)
            throw new IllegalStateException("Failed to delete the image with id = " + imageId);
    }
}
