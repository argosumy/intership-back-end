package com.spd.baraholka.notification.model;




import java.time.LocalDateTime;


public abstract class Notification {
    private String subject;
    private String description;
    private String sendTo;
    private String status;
    private String event;
    private LocalDateTime date;
    private String profileLinkUser;

    public Notification(){
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

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
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

    public String getProfileLinkUser() {
        return profileLinkUser;
    }

    public void setProfileLinkUser(String profileLinkUser) {
        this.profileLinkUser = profileLinkUser;
    }
}
