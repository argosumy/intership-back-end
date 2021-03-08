package com.spduniversity.services;

import com.spduniversity.entities.comments.Comment;
import com.spduniversity.repositories.comments.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAllByAdId(int adId) {
        List<Comment> result = commentRepository.getAllByAdId(adId);
        logger.info("IN getAllByAdId - comments found: {}", result.size());
        return result;
    }

    public Comment saveNew(Comment comment) {
        Comment commentToSave = commentRepository.saveNew(comment);
        logger.info("IN save new - comment: {} successfully saved", commentToSave);
        return commentToSave;
    }

    public void deleteById(int id) {
        commentRepository.deleteById(id);
        logger.info("IN delete - comment with id: {} successfully deleted", id);
    }

    public Optional<Comment> findById(int id) {
        Optional<Comment> result = commentRepository.findById(id);

        if (result.isEmpty()) {
            logger.warn("IN findById - no comment found by id: {}", id);
        }
        logger.info("IN findById - comment: {} found by id: {}", result, id);
        return result;
    }

    public Comment update(Comment comment, int id) {
        Comment updatedComment = commentRepository.update(comment, id);
        logger.info("IN update - comment: {} by id: {} successfully updated", updatedComment, id);
        return updatedComment;
    }
}
