package com.spd.baraholka.notification.model;

import java.time.LocalDateTime;

public class BanBlockNotification extends BaseNotification {

    private String reason;
    private LocalDateTime endDate;

    public BanBlockNotification() {
        super();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}