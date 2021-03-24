package com.spd.baraholka.user.persistance.entities;

import com.spd.baraholka.role.Role;

import java.util.*;
import java.time.LocalDateTime;

public class User {

    private int id;
    private String avatar;
    private String firstName;
    private String lastName;
    private String email;
    private String location;
    private String position;
    private String phoneNumber;
    private boolean isBlocked;
    private LocalDateTime endDateOfBan;
    private List<String> resourceLinks;
    private final Set<Role> roles;

    public User() {
        this.roles = new HashSet<>();
        roles.add(Role.USER);
    }

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public List<String> getResourceLinks() {
        return resourceLinks;
    }

    public void setResourceLinks(List<String> resourceLinks) {
        this.resourceLinks = resourceLinks;
    }

    public Set<Role> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

    public boolean grantRole(Role role) {
        return roles.add(role);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id && isBlocked == user.isBlocked && Objects.equals(avatar, user.avatar)
                && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName)
                && Objects.equals(email, user.email) && Objects.equals(position, user.position)
                && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(resourceLinks, user.resourceLinks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, avatar, firstName, lastName, email, position, phoneNumber, isBlocked, resourceLinks);
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
                ", isBlocked=" + isBlocked +
                ", resourceLinks=" + resourceLinks +
                '}';
    }

    public LocalDateTime getEndDateOfBan() {
        return endDateOfBan;
    }

    public void setEndDateOfBan(LocalDateTime endDateOfBan) {
        this.endDateOfBan = endDateOfBan;
    }
}
