package com.spduniversity.entities.comments;

import com.spduniversity.entities.advertisements.Advertisement;
import com.spduniversity.entities.users.User;

import java.time.LocalDate;

public class Comment {

    private int id;
    private String body;
    private LocalDate createdDate;
    private Advertisement advertisement;
    private User user;
    private Comment parent;

    public Comment() {
    }

    public Comment(int id, String body, LocalDate createdDate, Advertisement advertisement, User user, Comment parent) {
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

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }
}
