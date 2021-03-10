package com.spd.baraholka.comment_reactions.dto;

import com.spd.baraholka.comment_reactions.enums.CommentReactionType;
import com.spd.baraholka.comments.entities.Comment;
import com.spd.baraholka.users.entities.User;

public class CommentReactionDto {
    private int id;
    private final CommentReactionType commentReactionType;
    private Comment comment;
    private final User user;

    public CommentReactionDto(int id, CommentReactionType commentReactionType, Comment comment, User user) {
        this.id = id;
        this.commentReactionType = commentReactionType;
        this.comment = comment;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CommentReactionType getCommentReactionType() {
        return commentReactionType;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }
}
