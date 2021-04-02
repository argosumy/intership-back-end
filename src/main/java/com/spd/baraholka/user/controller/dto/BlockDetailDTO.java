package com.spd.baraholka.user.controller.dto;

import com.spd.baraholka.annotation.advertisement.PresentOrFutureDate;
import com.spd.baraholka.annotation.user.UserExist;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class BlockDetailDTO {

    @NotNull
    @Min(1)
    @UserExist
    private int userId;

    @PresentOrFutureDate
    private LocalDateTime blockedUntil;

    @NotEmpty
    @Size(max = 200)
    private String reason;

    @NotNull
    private boolean isNotify;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getBlockedUntil() {
        return blockedUntil;
    }

    public void setBlockedUntil(LocalDateTime blockedUntil) {
        this.blockedUntil = blockedUntil;
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
