package com.spduniversity.services;

import com.spduniversity.entities.comments.Comment;
import com.spduniversity.repositories.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
