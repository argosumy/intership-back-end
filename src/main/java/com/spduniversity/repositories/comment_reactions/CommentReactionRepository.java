package com.spduniversity.repositories.comment_reactions;


import com.spduniversity.entities.comments.CommentReaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommentReactionRepository implements CommentReactionPersistence {

    private final JdbcTemplate jdbcTemplate;

    public CommentReactionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int getTotalReactionTypeByCommentId(int commentId, String commentReaction) {
        final int update = jdbcTemplate.update(
                "SELECT COUNT(*) FROM comment_reactions WHERE comment_id=? AND reaction=?",
                commentId, commentReaction
        );
        return update;
    }

    @Override
    public CommentReaction saveNew(CommentReaction commentReaction) {
        jdbcTemplate.update(
                "INSERT INTO comment_reactions (reaction, comment_id, user_id) VALUES (?, ?, ?)",
                commentReaction.getCommentReaction().name(),
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
}
