package com.spd.baraholka.comments.services;

import com.spd.baraholka.comments.entities.Comment;
import com.spd.baraholka.comments.mappers.CommentUserInfoDtoMapper;
import com.spd.baraholka.comments.repositories.CommentRepository;
import com.spd.baraholka.notification.service.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);
    @Value("${commentService.topCommentsCount}")
    private int topCommentsCount;
    private final CommentRepository commentRepository;
    private final CommentUserInfoDtoMapper commentUserInfoDtoMapper;
    private final Sender sender;

    public CommentService(CommentRepository commentRepository, CommentUserInfoDtoMapper commentUserInfoDtoMapper, @Lazy Sender sender) {
        this.commentRepository = commentRepository;
        this.commentUserInfoDtoMapper = commentUserInfoDtoMapper;
        this.sender = sender;
    }

    public List<Comment> getAllByAdId(int adId) {
        List<Comment> result = commentRepository.getAllByAdId(adId);
        logger.info("IN getAllByAdId - comments found: {}", result.size());
        return result;
    }

    public List<Comment> getLimitCommentsByAdId(int adId) {
        return getAllByAdId(adId).stream()
                .sorted(Comparator.comparing(Comment::getCreatedDate).reversed())
                .limit(topCommentsCount)
                .collect(Collectors.toList());
    }

    public int saveNew(Comment comment) {
        int savedCommentId = commentRepository.saveNew(comment);
        var commentUserInfoDto = commentUserInfoDtoMapper.getCommentUserInfoDto(comment);
        commentUserInfoDto.setId(savedCommentId);
        logger.info("IN save new - comment with id : {} successfully saved", savedCommentId);
        sender.sendAdvertisementCommentNotification(commentUserInfoDto);
        sender.sendCommentNotification(commentUserInfoDto);

        return savedCommentId;
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
