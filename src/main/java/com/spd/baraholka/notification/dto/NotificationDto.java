package com.spd.baraholka.notification.dto;

import com.spd.baraholka.notification.enums.EventType;
import jdk.jshell.EvalException;

import java.time.LocalDateTime;

public class NotificationDto {

    private int userId;
    private int userMailToId;
    private int advertisementId;
    private String reason;
    private String objectLink;
    private String userProfileLink;
    private EventType eventType;
    private LocalDateTime blockEndDate;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserMailToId() {
        return userMailToId;
    }

    public void setUserMailToId(int userMailToId) {
        this.userMailToId = userMailToId;
    }

    public int getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(int advertisementId) {
        this.advertisementId = advertisementId;
    }

    public String getObjectLink() {
        return objectLink;
    }

    public void setObjectLink(String objectLink) {
        this.objectLink = objectLink;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getBlockEndDate() {
        return blockEndDate;
    }

    public void setBlockEndDate(LocalDateTime blockEndDate) {
        this.blockEndDate = blockEndDate;
    }

    public String getUserProfileLink() {
        return userProfileLink;
    }

    public void setUserProfileLink(String userProfileLink) {
        this.userProfileLink = userProfileLink;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
}
