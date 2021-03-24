package com.spd.baraholka.notification.model;

import java.time.LocalDateTime;

public class BanBlockNotification extends BaseNotification {
    private LocalDateTime banDateNotification;

    public BanBlockNotification() {
        super();
    }

    public LocalDateTime getBanDateNotification() {
        return banDateNotification;
    }

    public void setBanDateNotification(LocalDateTime banDateNotification) {
        this.banDateNotification = banDateNotification;
    }

}