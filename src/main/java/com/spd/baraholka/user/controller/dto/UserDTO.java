package com.spd.baraholka.user.controller.dto;

import org.json.simple.JSONObject;

public class UserDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String position;
    private String phoneNumber;
    private boolean isBlocked;
    private JSONObject additionalContactResources;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public JSONObject getAdditionalContactResources() {
        return additionalContactResources;
    }

    public void setAdditionalContactResources(JSONObject additionalContactResources) {
        this.additionalContactResources = additionalContactResources;
    }
}
