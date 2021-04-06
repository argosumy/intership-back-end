package com.spd.baraholka.comments.dto;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class CommentDto {

    private int id;
    @NotBlank(message = "body shouldn't be empty!")
    @Size(min = 2, max = 255, message = "length must be between 2 and 255 characters!")
    private final String body;
    @NotNull
    private final LocalDate createdDate;
    @Positive
    private final int advertisementId;
    @Positive
    private final int userId;
    @PositiveOrZero
    private final int parentCommentId;

    public CommentDto(int id, String body, LocalDate createdDate, int advertisementId, int userId, int parentCommentId) {
        this.id = id;
        this.body = body;
        this.createdDate = createdDate;
        this.advertisementId = advertisementId;
        this.userId = userId;
        this.parentCommentId = parentCommentId;
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

    public int getAdvertisementId() {
        return advertisementId;
    }

    public int getUserId() {
        return userId;
    }

    public int getParentCommentId() {
        return parentCommentId;
    }
}
