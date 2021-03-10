package com.spd.baraholka.comment_reactions.mappers;

import com.spd.baraholka.comment_reactions.dto.CommentReactionDto;
import com.spd.baraholka.comment_reactions.entities.CommentReaction;
import org.springframework.stereotype.Component;

@Component
public class CommentReactionDtoMapper {

    public CommentReaction toCommentReaction(CommentReactionDto commentReactionDto) {
        CommentReaction commentReaction = new CommentReaction();
        commentReaction.setId(commentReactionDto.getId());
        commentReaction.setCommentReactionType(commentReactionDto.getCommentReactionType());
        commentReaction.setComment(commentReactionDto.getComment());
        commentReaction.setUser(commentReactionDto.getUser());
        return commentReaction;
    }
}
