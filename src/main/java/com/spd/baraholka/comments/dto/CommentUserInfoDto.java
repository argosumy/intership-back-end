package com.spd.baraholka.comments.dto;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class CommentUserInfoDto {

    private int id;
    @NotBlank(message = "body shouldn't be empty!")
    @Size(min = 2, max = 255, message = "length must be between 2 and 255 characters!")
    private String body;
    @NotNull
    private LocalDate createdDate;
    @Positive
    private int advertisementId;
    @Positive
    private int userId;
    @PositiveOrZero
    private int parentCommentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public int getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(int advertisementId) {
        this.advertisementId = advertisementId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(int parentCommentId) {
        this.parentCommentId = parentCommentId;
    }
}
