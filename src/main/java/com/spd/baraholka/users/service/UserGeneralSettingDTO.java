package com.spd.baraholka.users.service;

import javax.validation.constraints.NotNull;

public class UserGeneralSettingDTO {

    @NotNull
    private int id;

    @NotNull
    private int userId;

    @NotNull
    private boolean openAdsInNewTab;

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

    public boolean isOpenAdsInNewTab() {
        return openAdsInNewTab;
    }

    public void setOpenAdsInNewTab(boolean openAdsInNewTab) {
        this.openAdsInNewTab = openAdsInNewTab;
    }
}
