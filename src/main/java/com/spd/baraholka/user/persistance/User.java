package com.spd.baraholka.user.persistance;

import java.util.List;

public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String position;
    private String phoneNumber;
    private boolean isBlocked;
    private List<UserAdditionalResource> additionalContactResources;

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

    public List<UserAdditionalResource> getAdditionalContactResources() {
        return additionalContactResources;
    }

    public void setAdditionalContactResources(List<UserAdditionalResource> additionalContactResources) {
        this.additionalContactResources = additionalContactResources;
    }
}
