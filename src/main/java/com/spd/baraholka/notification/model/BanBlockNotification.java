package com.spd.baraholka.notification.model;

import java.time.LocalDateTime;

public class BanBlockNotification extends BaseNotification implements Notification {
    private LocalDateTime dateBanNotification;
//    private String nameAd;

    public BanBlockNotification() {
        super();
    }

    public LocalDateTime getDateBanNotification() {
        return dateBanNotification;
    }

    public void setDateBanNotification(LocalDateTime dateBanNotification) {
        this.dateBanNotification = dateBanNotification;
    }

//    public String getNameAd() {
//        return nameAd;
//    }
//
//    public void setNameAd(String nameAd) {
//        this.nameAd = nameAd;
//    }
}