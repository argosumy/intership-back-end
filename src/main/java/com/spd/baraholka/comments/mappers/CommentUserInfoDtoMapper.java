package com.spd.baraholka.comments.mappers;

import com.spd.baraholka.comment_reactions.enums.CommentReactionType;
import com.spd.baraholka.comment_reactions.services.CommentReactionService;
import com.spd.baraholka.comments.dto.CommentUserInfoDto;
import com.spd.baraholka.comments.entities.Comment;
import com.spd.baraholka.user.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentUserInfoDtoMapper {

    private final UserService userService;
    private final CommentReactionService commentReactionService;

    public CommentUserInfoDtoMapper(UserService userService, CommentReactionService commentReactionService) {
        this.userService = userService;
        this.commentReactionService = commentReactionService;
    }

    public List<CommentUserInfoDto> toCommentUserInfoDtoList(List<Comment> commentList) {
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
        commentUserInfoDto.setLikesAmount(getLikesAmount(comment));
        commentUserInfoDto.setDislikesAmount(getDislikesAmount(comment));
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

    private int getLikesAmount(Comment comment) {
        return commentReactionService.getTotalReactionTypeByCommentId(comment.getId(), CommentReactionType.LIKE);
    }

    private int getDislikesAmount(Comment comment) {
        return commentReactionService.getTotalReactionTypeByCommentId(comment.getId(), CommentReactionType.DISLIKE);
    }
}
