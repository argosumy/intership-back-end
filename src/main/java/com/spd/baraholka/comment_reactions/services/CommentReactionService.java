package com.spd.baraholka.comment_reactions.services;

import com.spd.baraholka.comment_reactions.entities.CommentReaction;
import com.spd.baraholka.comment_reactions.enums.CommentReactionType;
import com.spd.baraholka.comment_reactions.repositories.CommentReactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CommentReactionService {
    private static final Logger logger = LoggerFactory.getLogger(CommentReactionService.class);

    private final CommentReactionRepository commentReactionRepository;

    public CommentReactionService(CommentReactionRepository commentReactionRepository) {
        this.commentReactionRepository = commentReactionRepository;
    }

    public int getTotalReactionTypeByCommentId(int commentId, CommentReactionType commentReactionType) {
        int size = commentReactionRepository.getTotalReactionTypeByCommentId(commentId, commentReactionType);
        logger.debug("IN getTotalAmountByCommentId - total size: {}", size);
        return size;
    }

    public CommentReaction saveNew(CommentReaction commentReaction) {
        CommentReaction commentReactionToSave = commentReactionRepository.saveNew(commentReaction);
        logger.info("New comment reaction: {} has been successfully saved", commentReactionToSave);
        return commentReactionToSave;
    }

    public void deleteLastRecordByCommentId(int commentId, CommentReactionType commentReactionType) {
        commentReactionRepository.deleteLastRecordByCommentId(commentId, commentReactionType);
        logger.info("IN delete - comment reaction: {} by comment id: {} successfully deleted",
                commentReactionType, commentId);
    }
}
