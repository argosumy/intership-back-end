package com.spduniversity.notifications.model;




import java.time.LocalDateTime;


public class Notification {
    private int id;
    private String subject;
    private String description;
    private User sendTo;
    private User sendFrom;
    private String status;
    private String event;
    private LocalDateTime date;

    public Notification(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getSendTo() {
        return sendTo;
    }

    public void setSendTo(User sendTo) {
        this.sendTo = sendTo;
    }

    public User getSendFrom() {
        return sendFrom;
    }

    public void setSendFrom(User sendFrom) {
        this.sendFrom = sendFrom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }


}
