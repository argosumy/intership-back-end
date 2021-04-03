package com.spd.baraholka.config.exceptions;

public class NotificationSendException extends RuntimeException {

    public NotificationSendException(String type) {
        super("Could not send notification: " + type);
    }
}

