package com.spd.baraholka.user.controller.dto;

import java.time.LocalDateTime;

public class ShortViewBlockDetailDTO {

    private boolean isBlocked;
    private LocalDateTime blockedUntil;

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public LocalDateTime getBlockedUntil() {
        return blockedUntil;
    }

    public void setBlockedUntil(LocalDateTime blockedUntil) {
        this.blockedUntil = blockedUntil;
    }
}
