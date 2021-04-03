package com.spd.baraholka.user.controller.dto;

import com.spd.baraholka.annotation.user.BelongToUser;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@BelongToUser
public class UserAdditionalResourceDTO {

    @NotEmpty
    @Size(max = 50)
    private String resourceName;

    @NotNull
    @Positive
    private int userId;

    private int id;
    private String resourceUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }
}
