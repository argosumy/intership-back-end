package com.spd.baraholka.user.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserAdditionalResourceDTO {

    @NotEmpty
    @Size(max = 50)
    private String resourceName;

    @NotEmpty
    private String resourceUrl;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
