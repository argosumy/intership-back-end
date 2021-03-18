package com.spd.baraholka.comments.mappers;

import com.spd.baraholka.advertisements.persistance.Advertisement;
import com.spd.baraholka.comments.dto.CommentDto;
import com.spd.baraholka.comments.entities.Comment;
import com.spd.baraholka.user.persistance.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentDtoMapper {

    public List<CommentDto> toCommentDtoList(List<Comment> commentList) {
        return commentList.stream()
                .map(this::getCommentDto)
                .collect(Collectors.toList());
    }

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
                commentDto.getCreatedDate(),
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

    public Comment updateExistsComment(Comment comment, CommentDto commentDto) {
        comment.setId(commentDto.getId());
        comment.setBody(commentDto.getBody());
        comment.setCreatedDate(commentDto.getCreatedDate());
        comment.setAdvertisement(getAdvertisement(commentDto));
        comment.setUser(getUser(commentDto));
        comment.setParent(getParentComment(commentDto));
        return comment;
    }
}
