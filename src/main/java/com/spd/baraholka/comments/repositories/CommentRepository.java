package com.spd.baraholka.comments.repositories;


import com.spd.baraholka.comments.entities.Comment;
import com.spd.baraholka.comments.mappers.CommentRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepository implements CommentPersistence {

    private final JdbcTemplate jdbcTemplate;

    public CommentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Comment> getAllByAdId(int adId) {
        return jdbcTemplate.query(
                "SELECT c.*, a.id AS ad_id, u.* FROM comments c " +
                        "INNER JOIN advertisements a ON c.advertisement_id = a.id " +
                        "INNER JOIN users u ON c.user_id = u.id WHERE c.advertisement_id=?",
                new CommentRowMapper(),
                adId
        );
    }

    @Override
    public Comment saveNew(Comment comment) {
        jdbcTemplate.update(
                "INSERT INTO comments (body, created_at, advertisement_id, user_id, parent_id) VALUES (?, ?, ?, ?, ?)",
                comment.getBody(),
                comment.getCreatedDate(),
                comment.getAdvertisement().getId(),
                comment.getUser().getId(),
                comment.getParent().getId()
        );
        return comment;
    }

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM comments WHERE id=?", id);
    }

    @Override
    public Optional<Comment> findById(int id) {
        String sql = "SELECT c.*, a.id AS ad_id, u.* FROM comments c " +
                "INNER JOIN advertisements a ON c.advertisement_id = a.id " +
                "INNER JOIN users u ON c.user_id = u.id WHERE c.id =" + id;

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new CommentRowMapper()));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Comment update(Comment updatedComment, int id) {
        jdbcTemplate.update(
                "UPDATE comments SET body=?, created_at=?, advertisement_id=?, user_id=?, parent_id=? WHERE id=?",
                updatedComment.getBody(),
                updatedComment.getCreatedDate(),
                updatedComment.getAdvertisement().getId(),
                updatedComment.getUser().getId(),
                updatedComment.getParent().getId(),
                id
        );
        return updatedComment;
    }
}
