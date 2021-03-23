package com.spd.baraholka.user.persistance.entities;

public class UserNotificationSettings {

    private int id;
    private int userId;
    private boolean newAdNotification;
    private boolean newCommentToAdNotification;
    private boolean replyToCommentNotification;
    private boolean mentionInNotificationThread;
    private boolean wishlistUpdateNotification;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int isUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isNewAdNotification() {
        return newAdNotification;
    }

    public void setNewAdNotification(boolean newAdNotification) {
        this.newAdNotification = newAdNotification;
    }

    public boolean isNewCommentToAdNotification() {
        return newCommentToAdNotification;
    }

    public void setNewCommentToAdNotification(boolean newCommentToAdNotification) {
        this.newCommentToAdNotification = newCommentToAdNotification;
    }

    public boolean isReplyToCommentNotification() {
        return replyToCommentNotification;
    }

    public void setReplyToCommentNotification(boolean replyToCommentNotification) {
        this.replyToCommentNotification = replyToCommentNotification;
    }

    public boolean isMentionInNotificationThread() {
        return mentionInNotificationThread;
    }

    public void setMentionInNotificationThread(boolean mentionInNotificationThread) {
        this.mentionInNotificationThread = mentionInNotificationThread;
    }

    public boolean isWishlistUpdateNotification() {
        return wishlistUpdateNotification;
    }

    public void setWishlistUpdateNotification(boolean wishlistUpdateNotification) {
        this.wishlistUpdateNotification = wishlistUpdateNotification;
    }
}
