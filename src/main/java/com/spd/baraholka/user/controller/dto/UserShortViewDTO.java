package com.spd.baraholka.user.controller.dto;

import java.time.LocalDateTime;

public class UserShortViewDTO {

    private int id;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public LocalDateTime getEndDateOfBan() {
        return endDateOfBan;
    }

    public void setEndDateOfBan(LocalDateTime endDateOfBan) {
        this.endDateOfBan = endDateOfBan;
    }
}
