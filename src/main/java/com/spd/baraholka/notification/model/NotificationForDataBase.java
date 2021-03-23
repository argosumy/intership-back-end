package com.spd.baraholka.notification.model;

import java.util.Date;

public class NotificationForDataBase {
    private int id;
    private String description;
    private int recipient;
    private int writer;
    private int advertisement;
    private int status;
    private int event;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRecipient() {
        return recipient;
    }

    public void setRecipient(int recipient) {
        this.recipient = recipient;
    }

    public int getWriter() {
        return writer;
    }

    public void setWriter(int writer) {
        this.writer = writer;
    }

    public int getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(int advertisement) {
        this.advertisement = advertisement;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
