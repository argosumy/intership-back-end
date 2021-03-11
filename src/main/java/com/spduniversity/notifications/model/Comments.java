package com.spduniversity.notifications.model;

public class Comments extends Notification {
    private String content;
    private User userFrom;
    private Advertisemenets toAd;
    private User toUser;

    public Comments(String content) {
        super();
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(User userFrom) {
        this.userFrom = userFrom;
    }

    public Advertisemenets getToAd() {
        return toAd;
    }

    public void setToAd(Advertisemenets toAd) {
        this.toAd = toAd;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }
}
