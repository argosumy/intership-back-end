package com.spd.baraholka.user.controller.dto;

import java.time.LocalDateTime;

public class UserShortViewDTO {

    private int id;
    private String imageUrl;
    private String firstName;
    private String lastName;
    private boolean isBlocked;
    private LocalDateTime endDateOfBan;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public void setEndDateOfBan(LocalDateTime endDateOfBan) {
        this.endDateOfBan = endDateOfBan;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public LocalDateTime getEndDateOfBan() {
        return endDateOfBan;
    }
}
