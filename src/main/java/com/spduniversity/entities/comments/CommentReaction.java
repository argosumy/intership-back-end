package com.spduniversity.entities.comments;

import com.spduniversity.entities.enums.CommentReactionType;
import com.spduniversity.entities.users.User;

public class CommentReaction {

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

    public void setCommentReaction(CommentReactionType commentReaction) {
        this.commentReaction = commentReaction;
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

    public void setUser(User user) {
        this.user = user;
    }
}
