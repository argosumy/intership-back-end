package com.spd.baraholka.notification.model;

public class AdvertisementNotification extends BaseNotification {

    private String title;
    private String description;
//    private String image;


    public AdvertisementNotification() {
        super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
