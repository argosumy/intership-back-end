package com.spd.baraholka.comments.repositories;

import com.spd.baraholka.comments.entities.Comment;
import com.spd.baraholka.comments.mappers.CommentRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Repository
public class CommentRepository implements CommentPersistence {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final CommentRowMapper commentRowMapper;

    public CommentRepository(NamedParameterJdbcTemplate jdbcTemplate, CommentRowMapper commentRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.commentRowMapper = commentRowMapper;
    }

    @Override
    public List<Comment> getAllByAdId(int adId) {
        return jdbcTemplate.query(
                "SELECT c.*, a.id AS ad_id, u.* FROM comments c " +
                        "INNER JOIN advertisements a ON c.advertisement_id = a.id " +
                        "INNER JOIN users u ON c.user_id = u.id WHERE c.advertisement_id=:adId",
                Map.of("adId", adId),
                commentRowMapper
        );
    }

    @Override
    public int saveNew(Comment comment) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                "INSERT INTO comments (body, created_at, advertisement_id, user_id, parent_id) " +
                        "VALUES (:body, :createdDate, :adId, :userId, :parentId)",
                saveCommentParameters(comment),
                keyHolder,
                new String[] {"id"});
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM comments WHERE id=:id",
                Map.of("id", id));
    }

    @Override
    public Optional<Comment> findById(int id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(
                    "SELECT c.*, a.id AS ad_id, u.* FROM comments c " +
                            "INNER JOIN advertisements a ON c.advertisement_id = a.id " +
                            "INNER JOIN users u ON c.user_id = u.id WHERE c.id=:id",
                    Map.of("id", id),
                    commentRowMapper)
            );
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Comment update(Comment updatedComment, int id) {
        jdbcTemplate.update(
                "UPDATE comments SET body=:body, created_at=:createdDate, advertisement_id=:adId, user_id=:userId, parent_id=:parentId WHERE id=:id",
                Map.of("body", updatedComment.getBody(),
                        "createdDate", updatedComment.getCreatedDate(),
                        "adId", updatedComment.getAdvertisement().getAdvertisementId(),
                        "userId", updatedComment.getUser().getId(),
                        "parentId", updatedComment.getParent().getId(),
                        "id", id)
        );
        return updatedComment;
    }

    private MapSqlParameterSource saveCommentParameters(Comment comment) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("body", comment.getBody());
        namedParameters.addValue("createdDate", comment.getCreatedDate());
        namedParameters.addValue("adId", comment.getAdvertisement().getAdvertisementId());
        namedParameters.addValue("userId", comment.getUser().getId());
        namedParameters.addValue("parentId", comment.getParent().getId());
        return namedParameters;
    }
}
