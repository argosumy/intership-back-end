package com.spd.baraholka.user;

public class User {

    private Long id;
    private String avatar;
    private String firstName;
    private String lastName;
    private String email;
    private String location;
    private String phoneNumber;
    private String position;

    public Long getId() {
        return id;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getLocation() {
        return location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
