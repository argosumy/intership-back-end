package com.spd.baraholka.comments.mappers;

import com.spd.baraholka.advertisement.persistance.entities.Advertisement;
import com.spd.baraholka.comments.dto.CommentDto;
import com.spd.baraholka.comments.dto.UpdatedCommentDto;
import com.spd.baraholka.comments.entities.Comment;
import com.spd.baraholka.user.persistance.entities.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CommentDtoMapper {

    public CommentDto getCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getBody(),
                comment.getCreatedDate(),
                comment.getAdvertisement().getAdvertisementId(),
                comment.getUser().getId(),
                comment.getParent().getId()
        );
    }

    public Comment toComment(CommentDto commentDto) {
        return new Comment(
                commentDto.getId(),
                commentDto.getBody(),
                LocalDate.now(),
                getAdvertisement(commentDto),
                getUser(commentDto),
                getParentComment(commentDto)
        );
    }

    private Comment getParentComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getParentCommentId());
        return comment;
    }

    private User getUser(CommentDto commentDto) {
        User user = new User();
        user.setId(commentDto.getUserId());
        return user;
    }

    private Advertisement getAdvertisement(CommentDto commentDto) {
        Advertisement advertisement = new Advertisement();
        advertisement.setAdvertisementId(commentDto.getAdvertisementId());
        return advertisement;
    }

    public Comment updateExistsComment(Comment comment, UpdatedCommentDto updatedCommentDto) {
        comment.setBody(updatedCommentDto.getBody());
        comment.setCreatedDate(LocalDate.now());
        return comment;
    }
}
