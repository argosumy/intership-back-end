package com.spd.baraholka.notification.dao;

import com.spd.baraholka.notification.enums.EventType;

import java.time.LocalDateTime;

public class NotificationDao {

    private int userMailToId;
    private int advertisementId;
    private EventType eventType;
    private LocalDateTime sendDate;

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

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public LocalDateTime getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDateTime sendDate) {
        this.sendDate = sendDate;
    }
}
