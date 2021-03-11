package com.spd.baraholka.comment_reactions.dto;

import com.spd.baraholka.comment_reactions.enums.CommentReactionType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CommentReactionDto {

    private int id;
    @NotNull
    private final CommentReactionType commentReactionType;
    @Positive
    private final int commentId;
    @Positive
    private final int userId;

    public CommentReactionDto(int id, CommentReactionType commentReactionType, int commentId, int userId) {
        this.id = id;
        this.commentReactionType = commentReactionType;
        this.commentId = commentId;
        this.userId = userId;
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

    public int getCommentId() {
        return commentId;
    }

    public int getUserId() {
        return userId;
    }
}
