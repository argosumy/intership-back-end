package com.spd.baraholka.notification.model;

public class CommentNotification extends BaseNotification {

    private String writerName;

    public CommentNotification() {
        super();
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }
}