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
    @NotNull
    private String userName;
    @NotNull
    private String userLastName;
    @NotNull
    private String userImageUrl;
    @PositiveOrZero
    private int parentCommentId;
    @PositiveOrZero
    private int likesAmount;
    @PositiveOrZero
    private int dislikesAmount;

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

    public int getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(int parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public int getLikesAmount() {
        return likesAmount;
    }

    public void setLikesAmount(int likesAmount) {
        this.likesAmount = likesAmount;
    }

    public int getDislikesAmount() {
        return dislikesAmount;
    }

    public void setDislikesAmount(int dislikesAmount) {
        this.dislikesAmount = dislikesAmount;
    }
}
