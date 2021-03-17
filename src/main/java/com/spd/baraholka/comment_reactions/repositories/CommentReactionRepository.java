package com.spd.baraholka.comment_reactions.repositories;

import com.spd.baraholka.comment_reactions.entities.CommentReaction;
import com.spd.baraholka.comment_reactions.enums.CommentReactionType;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class CommentReactionRepository implements CommentReactionPersistence {

    public static final String REACTION = "reaction";
    public static final String COMMENT_ID = "commentId";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CommentReactionRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public CommentReaction saveNew(CommentReaction commentReaction) {
        jdbcTemplate.update(
                "INSERT INTO comment_reactions (reaction, comment_id, user_id) VALUES (:reaction, :commentId, :userId)",
                Map.of(REACTION, commentReaction.getCommentReactionType().name(),
                        COMMENT_ID, commentReaction.getComment().getId(),
                        "userId", commentReaction.getUser().getId())
        );
        return commentReaction;
    }

    @Override
    public void deleteLastRecordByCommentId(int commentId, CommentReactionType commentReactionType) {
        jdbcTemplate.update(
                "DELETE FROM comment_reactions WHERE id=" +
                        "(SELECT id FROM comment_reactions WHERE comment_id=:commentId AND reaction=:reaction ORDER BY id DESC LIMIT 1)",
                Map.of(COMMENT_ID, commentId,
                        REACTION, commentReactionType.name())
        );
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public int getTotalReactionTypeByCommentId(int commentId, CommentReactionType commentReactionType) {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(id) FROM comment_reactions WHERE comment_id=:commentId AND reaction=:reaction",
                Map.of(COMMENT_ID, commentId,
                        REACTION, commentReactionType.name()),
                Integer.class
        );
    }
}
