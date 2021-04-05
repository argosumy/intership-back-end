package com.spd.baraholka.user.controller.dto;

import com.spd.baraholka.role.Role;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class UserDTO {

    @NotNull
    private int id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    @NotEmpty
    @Size(max = 50)
    private String position;

    @NotEmpty
    @Size(max = 20)
    private String phoneNumber;

    @Valid
    @NotEmpty
    private List<UserAdditionalResourceDTO> additionalContactResources;

    private boolean isBlocked;

    @NotEmpty
    private Set<Role> roles;

    private String imageUrl;
    private LocalDateTime endDateOfBan;
    private String location;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

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

    public List<UserAdditionalResourceDTO> getAdditionalContactResources() {
        return additionalContactResources;
    }

    public void setAdditionalContactResources(List<UserAdditionalResourceDTO> additionalContactResources) {
        this.additionalContactResources = additionalContactResources;
    }

    public LocalDateTime getEndDateOfBan() {
        return endDateOfBan;
    }

    public void setEndDateOfBan(LocalDateTime endDateOfBan) {
        this.endDateOfBan = endDateOfBan;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Role> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
