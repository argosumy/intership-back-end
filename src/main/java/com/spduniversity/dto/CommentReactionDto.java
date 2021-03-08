package com.spduniversity.dto;

import com.spduniversity.entities.comments.Comment;
import com.spduniversity.entities.comments.CommentReaction;
import com.spduniversity.entities.enums.CommentReactionType;
import com.spduniversity.entities.users.User;

public class CommentReactionDto {
    private int id;
    private CommentReactionType commentReaction;
    private Comment comment;
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CommentReactionType getCommentReaction() {
        return commentReaction;
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

    public static CommentReaction toCommentReaction(CommentReactionDto commentReactionDto) {
        CommentReaction commentReaction = new CommentReaction();
        commentReaction.setId(commentReactionDto.getId());
        commentReaction.setCommentReaction(commentReactionDto.getCommentReaction());
        commentReaction.setComment(commentReactionDto.getComment());
        commentReaction.setUser(commentReactionDto.getUser());

        return commentReaction;
    }
}
