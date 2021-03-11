package com.spd.baraholka.comments.dto;

import com.spd.baraholka.advertisements.entities.Advertisement;
import com.spd.baraholka.comments.entities.Comment;
import com.spd.baraholka.users.entities.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class CommentDto {

    private int id;
    @NotBlank(message = "body shouldn't be empty!")
    @Size(min = 2, max = 255, message = "length must be between 2 and 255 characters!")
    private final String body;
    @NotNull
    private final LocalDate createdDate;
    @NotNull
    private final Advertisement advertisement;
    @NotNull
    private final User user;
    @NotNull
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
}
