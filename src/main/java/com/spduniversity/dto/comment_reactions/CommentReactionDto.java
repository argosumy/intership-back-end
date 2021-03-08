package com.spduniversity.dto.comment_reactions;

import com.spduniversity.entities.comments.Comment;
import com.spduniversity.entities.comment_reactions.CommentReaction;
import com.spduniversity.entities.enums.CommentReactionType;
import com.spduniversity.entities.users.User;

public class CommentReactionDto {
    private int id;
    private final CommentReactionType commentReaction;
    private Comment comment;
    private final User user;

    public CommentReactionDto(int id, CommentReactionType commentReaction, Comment comment, User user) {
        this.id = id;
        this.commentReaction = commentReaction;
        this.comment = comment;
        this.user = user;
    }

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
        commentReaction.setCommentReactionType(commentReactionDto.getCommentReaction());
        commentReaction.setComment(commentReactionDto.getComment());
        commentReaction.setUser(commentReactionDto.getUser());
        return commentReaction;
    }
}
