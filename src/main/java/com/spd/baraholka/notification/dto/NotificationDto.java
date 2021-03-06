package com.spd.baraholka.notification.dto;

import com.spd.baraholka.notification.enums.EventType;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

public class NotificationDto {

    @PositiveOrZero
    private int userId;
    @Positive
    private int userMailToId;
    @PositiveOrZero
    private int advertisementId;
    @NotNull()
    @Length(min = 5, max = 255)
    private String reason;
    @NotNull
    private String objectLink;
    @NotNull
    private String userProfileLink;
    @NotNull
    private EventType eventType;
    @FutureOrPresent
    private LocalDateTime blockEndDate;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserMailToId() {
        return userMailToId;
    }

    public void setUserMailToId(int userMailToId) {
        this.userMailToId = userMailToId;
    }

    public int getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(int advertisementId) {
        this.advertisementId = advertisementId;
    }

    public String getObjectLink() {
        return objectLink;
    }

    public void setObjectLink(String objectLink) {
        this.objectLink = objectLink;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getBlockEndDate() {
        return blockEndDate;
    }

    public void setBlockEndDate(LocalDateTime blockEndDate) {
        this.blockEndDate = blockEndDate;
    }

    public String getUserProfileLink() {
        return userProfileLink;
    }

    public void setUserProfileLink(String userProfileLink) {
        this.userProfileLink = userProfileLink;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
}
