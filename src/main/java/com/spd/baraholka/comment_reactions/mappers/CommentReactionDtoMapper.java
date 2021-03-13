package com.spd.baraholka.comment_reactions.mappers;

import com.spd.baraholka.comment_reactions.dto.CommentReactionDto;
import com.spd.baraholka.comment_reactions.entities.CommentReaction;
import com.spd.baraholka.comments.entities.Comment;
import com.spd.baraholka.users.entities.User;
import org.springframework.stereotype.Component;

@Component
public class CommentReactionDtoMapper {

    public CommentReaction toCommentReaction(CommentReactionDto commentReactionDto) {
        CommentReaction commentReaction = new CommentReaction();
        commentReaction.setId(commentReactionDto.getId());
        commentReaction.setCommentReactionType(commentReactionDto.getCommentReactionType());
        commentReaction.setComment(getComment(commentReactionDto));
        commentReaction.setUser(getUser(commentReactionDto));
        return commentReaction;
    }

    private Comment getComment(CommentReactionDto commentReactionDto) {
        Comment comment = new Comment();
        comment.setId(commentReactionDto.getCommentId());
        return comment;
    }

    private User getUser(CommentReactionDto commentReactionDto) {
        User user = new User();
        user.setId(commentReactionDto.getUserId());
        return user;
    }
}
