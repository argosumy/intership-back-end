package com.spd.baraholka.user.persistance.entities;

import com.spd.baraholka.role.Role;

import java.time.LocalDateTime;
import java.util.*;

public class User {

    private int id;
    private String imageUrl;
    private String firstName;
    private String lastName;
    private String email;
    private String location;
    private String position;
    private String phoneNumber;
    private boolean isBlocked;
    private LocalDateTime endDateOfBan;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public Set<Role> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

    public boolean grantRole(Role role) {
        return roles.add(role);
    }

    public LocalDateTime getEndDateOfBan() {
        return endDateOfBan;
    }

    public void setEndDateOfBan(LocalDateTime endDateOfBan) {
        this.endDateOfBan = endDateOfBan;
    }
}
