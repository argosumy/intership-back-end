package com.spd.baraholka.user.persistance.entities;

import java.time.LocalDateTime;

public class BlockDetail {

    private int userId;
    private LocalDateTime banedUntil;
    private String reason;
    private boolean isNotify;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getBanedUntil() {
        return banedUntil;
    }

    public void setBanedUntil(LocalDateTime banedUntil) {
        this.banedUntil = banedUntil;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isNotify() {
        return isNotify;
    }

    public void setNotify(boolean notify) {
        isNotify = notify;
    }
}
