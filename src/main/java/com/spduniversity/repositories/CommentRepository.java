package com.spduniversity.repositories;


import com.spduniversity.entities.comments.Comment;
import com.spduniversity.mappers.CommentMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepository implements CommentPersistence {

    private final JdbcTemplate jdbcTemplate;

    public CommentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Comment> getAllByAdId(int adId) {
        return jdbcTemplate.query(
                "SELECT c.*, a.id AS ad_id, u.id AS user_id, u.first_name, u.last_name, u.avatar, u.email, u.position, u.phone_number, u.blocked_status, " +
                        "u.resources_link FROM comments c " +
                        "INNER JOIN advertisements a ON c.advertisements_id = a.id " +
                        "INNER JOIN users u ON c.user_id = u.id WHERE c.advertisement_id=?",
                new Object[]{adId},
                new CommentMapper()
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
    public void remove(int commentId) {

    }

    @Override
    public void update(Comment comment) {

    }
}
