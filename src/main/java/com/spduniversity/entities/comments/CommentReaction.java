package com.spduniversity.entities.comments;

import com.spduniversity.entities.enums.CommentReactionType;
import com.spduniversity.entities.users.User;

import java.util.Objects;

public class CommentReaction {

    private int id;
    private CommentReactionType commentReactionType;
    private Comment comment;
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CommentReactionType getCommentReactionType() {
        return commentReactionType;
    }

    public void setCommentReactionType(CommentReactionType commentReactionType) {
        this.commentReactionType = commentReactionType;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommentReaction)) {
            return false;
        }
        CommentReaction that = (CommentReaction) o;
        return getId() == that.getId() &&
                getCommentReactionType() == that.getCommentReactionType() &&
                Objects.equals(getComment(), that.getComment()) &&
                Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCommentReactionType(), getComment(), getUser());
    }
}
