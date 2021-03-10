package com.spd.baraholka.comments.mappers;

import com.spd.baraholka.comments.dto.CommentDto;
import com.spd.baraholka.comments.entities.Comment;
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
                comment.getAdvertisement(),
                comment.getUser(),
                comment.getParent()
        );
    }

    public Comment toComment(CommentDto commentDto) {
        return new Comment(
                commentDto.getId(),
                commentDto.getBody(),
                commentDto.getCreatedDate(),
                commentDto.getAdvertisement(),
                commentDto.getUser(),
                commentDto.getParent()
        );
    }
}
