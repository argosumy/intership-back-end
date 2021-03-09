package com.spd.baraholka.comment_reactions.repositories;


import com.spd.baraholka.comment_reactions.entities.CommentReaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommentReactionRepository implements CommentReactionPersistence {

    private final JdbcTemplate jdbcTemplate;

    public CommentReactionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public CommentReaction saveNew(CommentReaction commentReaction) {
        jdbcTemplate.update(
                "INSERT INTO comment_reactions (reaction, comment_id, user_id) VALUES (?, ?, ?)",
                commentReaction.getCommentReactionType().name(),
                commentReaction.getComment().getId(),
                commentReaction.getUser().getId()
        );
        return commentReaction;
    }

    @Override
    public void deleteLastRecordByCommentId(int commentId, String commentReaction) {
        jdbcTemplate.update(
                "DELETE FROM comment_reactions WHERE id=" +
                        "(SELECT id FROM comment_reactions WHERE comment_id=? AND reaction=? ORDER BY id DESC LIMIT 1)",
                commentId, commentReaction
        );
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public int getTotalReactionTypeByCommentId(int commentId, String commentReaction) {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(id) FROM comment_reactions WHERE comment_id=? AND reaction=?",
                Integer.class,
                commentId, commentReaction
        );
    }
}
