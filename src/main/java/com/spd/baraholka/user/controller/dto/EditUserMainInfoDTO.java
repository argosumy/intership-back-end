package com.spd.baraholka.user.controller.dto;

import com.spd.baraholka.annotation.user.UserAdditionalResources;
import com.spd.baraholka.annotation.user.UserExist;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@UserAdditionalResources
public class EditUserMainInfoDTO {

    @NotNull
    @UserExist
    private int userId;

    @NotEmpty
    @Size(max = 50)
    private String position;

    @NotEmpty
    @Size(max = 20)
    private String phoneNumber;

    @NotEmpty
    @Size(max = 26)
    private String location;

    @NotEmpty
    @Size(max = 10)
    private List<UserAdditionalResourceDTO> additionalContactResources;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<UserAdditionalResourceDTO> getAdditionalContactResources() {
        return additionalContactResources;
    }

    public void setAdditionalContactResources(List<UserAdditionalResourceDTO> additionalContactResources) {
        this.additionalContactResources = additionalContactResources;
    }
}
