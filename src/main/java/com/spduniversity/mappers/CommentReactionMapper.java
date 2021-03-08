package com.spduniversity.mappers;

import com.spduniversity.entities.comments.Comment;
import com.spduniversity.entities.comments.CommentReaction;
import com.spduniversity.entities.enums.CommentReactionType;
import com.spduniversity.entities.users.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentReactionMapper implements RowMapper<CommentReaction> {

    @Override
    public CommentReaction mapRow(ResultSet rs, int rowNum) throws SQLException {
        CommentReaction commentReaction = new CommentReaction();
        commentReaction.setId(rs.getInt("id"));
        commentReaction.setCommentReaction(CommentReactionType.valueOf(rs.getString("reaction")));
        commentReaction.setComment(getMappedComment(rs));
        commentReaction.setUser(getMappedUser(rs));

        return commentReaction;
    }

    private Comment getMappedComment(ResultSet rs) throws SQLException {
        Comment comment = new Comment();
        comment.setId(rs.getInt("comment_id"));

        return comment;
    }

    private User getMappedUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("user_id"));

        return user;
    }
}
