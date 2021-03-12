package com.spduniversity.notifications.model;

public class NotificationCommentsAd extends Notification {
    private String linkAd;


    public NotificationCommentsAd() {
        super();
    }

    public String getLinkAd() {
        return linkAd;
    }

    public void setLinkAd(String linkAd) {
        this.linkAd = linkAd;
    }
}
