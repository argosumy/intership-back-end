package com.spduniversity.dto.comments;

import com.spduniversity.entities.advertisements.Advertisement;
import com.spduniversity.entities.comments.Comment;
import com.spduniversity.entities.users.User;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CommentDto {

    private int id;
    private final String body;
    private final LocalDate createdDate;
    private final Advertisement advertisement;
    private final User user;
    private final Comment parent;

    public CommentDto(int id, String body, LocalDate createdDate, Advertisement advertisement, User user, Comment parent) {
        this.id = id;
        this.body = body;
        this.createdDate = createdDate;
        this.advertisement = advertisement;
        this.user = user;
        this.parent = parent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public User getUser() {
        return user;
    }

    public Comment getParent() {
        return parent;
    }

    public static CommentDto toCommentDto(Comment comment) {
        return getCommentDto(comment);
    }

    public static List<CommentDto> toCommentDtoList(List<Comment> commentList) {
        return commentList.stream()
                .map(CommentDto::getCommentDto)
                .collect(Collectors.toList());
    }

    private static CommentDto getCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getBody(),
                comment.getCreatedDate(),
                comment.getAdvertisement(),
                comment.getUser(),
                comment.getParent()
        );
    }

    public static Comment toComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setBody(commentDto.getBody());
        comment.setCreatedDate(commentDto.getCreatedDate());
        comment.setAdvertisement(commentDto.getAdvertisement());
        comment.setUser(commentDto.getUser());
        comment.setParent(commentDto.getParent());
        return comment;
    }
}
