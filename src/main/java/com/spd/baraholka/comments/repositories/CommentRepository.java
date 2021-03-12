package com.spd.baraholka.comments.repositories;

import com.spd.baraholka.comments.entities.Comment;
import com.spd.baraholka.comments.mappers.CommentRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
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
    public Comment saveNew(Comment comment) {
        jdbcTemplate.update(
                "INSERT INTO comments (body, created_at, advertisement_id, user_id, parent_id) " +
                        "VALUES (:body, :createdDate, :adId, :userId, :parentId)",
                Map.of("body", comment.getBody(),
                        "createdDate", comment.getCreatedDate(),
                        "adId", comment.getAdvertisement().getId(),
                        "userId", comment.getUser().getId(),
                        "parentId", comment.getParent().getId())
        );
        return comment;
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
                        "adId", updatedComment.getAdvertisement().getId(),
                        "userId", updatedComment.getUser().getId(),
                        "parentId", updatedComment.getParent().getId(),
                        "id", id)
        );
        return updatedComment;
    }
}
