package com.spduniversity.services;

import com.spduniversity.entities.comments.CommentReaction;
import com.spduniversity.repositories.comment_reactions.CommentReactionRepository;
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

    public int getTotalAmountByCommentId(int commentId) {
        int size = commentReactionRepository.getTotalAmountByCommentId(commentId);
        logger.info("IN getTotalAmountByCommentId - total size: {}", size);
        return size;
    }

    public CommentReaction saveNew(CommentReaction commentReaction) {
        CommentReaction commentReactionToSave = commentReactionRepository.saveNew(commentReaction);
        logger.info("IN save new commentReaction: {} successfully saved", commentReactionToSave);
        return commentReactionToSave;
    }

    public void deleteLastRecordByCommentId(int commentId) {
        commentReactionRepository.deleteLastRecordByCommentId(commentId);
        logger.info("IN delete - comment reaction by comment id: {} successfully deleted", commentId);
    }
}
