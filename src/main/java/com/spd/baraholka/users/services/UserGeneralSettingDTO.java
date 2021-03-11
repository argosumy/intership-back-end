package com.spd.baraholka.users.services;

public class UserGeneralSettingDTO {

    private int id;
    private int userId;
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
