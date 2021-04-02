package com.spd.baraholka.comments.mappers;

import com.spd.baraholka.comments.dto.CommentUserInfoDto;
import com.spd.baraholka.comments.entities.Comment;
import com.spd.baraholka.user.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentUserInfoDtoMapper {

    private final UserService userService;

    public CommentUserInfoDtoMapper(UserService userService) {
        this.userService = userService;
    }

    public List<CommentUserInfoDto> toCommentDtoList(List<Comment> commentList) {
        return commentList.stream()
                .map(this::getCommentUserInfoDto)
                .collect(Collectors.toList());
    }

    public CommentUserInfoDto getCommentUserInfoDto(Comment comment) {
        CommentUserInfoDto commentUserInfoDto = new CommentUserInfoDto();
        commentUserInfoDto.setId(comment.getId());
        commentUserInfoDto.setBody(comment.getBody());
        commentUserInfoDto.setCreatedDate(comment.getCreatedDate());
        commentUserInfoDto.setAdvertisementId(comment.getAdvertisement().getAdvertisementId());
        commentUserInfoDto.setParentCommentId(comment.getParent().getId());
        commentUserInfoDto.setUserName(getUserName(comment));
        commentUserInfoDto.setUserLastName(getUserLastName(comment));
        commentUserInfoDto.setUserImageUrl(getUserImageUrl(comment));
        return commentUserInfoDto;
    }

    private String getUserName(Comment comment) {
        return userService.getUserById(comment.getUser().getId()).getFirstName();
    }

    private String getUserLastName(Comment comment) {
        return userService.getUserById(comment.getUser().getId()).getLastName();
    }

    private String getUserImageUrl(Comment comment) {
        return userService.getUserById(comment.getUser().getId()).getImageUrl();
    }
}
