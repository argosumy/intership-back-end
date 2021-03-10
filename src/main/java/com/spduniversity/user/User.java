package com.spduniversity.user;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class User {

    private int id;
    private String avatar;
    private String firstName;
    private String lastName;
    private String email;
    private String position;
    private String phoneNumber;
    private boolean blockedStatus;
    private String linkOfResources;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public boolean isBlockedStatus() {
        return blockedStatus;
    }

    public void setBlockedStatus(boolean blockedStatus) {
        this.blockedStatus = blockedStatus;
    }

    public String getLinkOfResources() {
        return linkOfResources;
    }

    public void setLinkOfResources(String linkOfResources) {
        this.linkOfResources = linkOfResources;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && blockedStatus == user.blockedStatus && Objects.equals(avatar, user.avatar) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email) && Objects.equals(position, user.position) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(linkOfResources, user.linkOfResources);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, avatar, firstName, lastName, email, position, phoneNumber, blockedStatus, linkOfResources);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", avatar='" + avatar + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", position='" + position + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", blockedStatus=" + blockedStatus +
                ", linkOfResources='" + linkOfResources + '\'' +
                '}';
    }
}
