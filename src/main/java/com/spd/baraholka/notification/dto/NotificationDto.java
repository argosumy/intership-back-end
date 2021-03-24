package com.spd.baraholka.notification.dto;

import com.spd.baraholka.notification.enums.NotificationStatus;

import java.time.LocalDateTime;
import java.util.List;

public class NotificationDto {

    private String subject;
    private String description;
    private String sendTo;
    private String adName;
    private String userProfileLink;
    private String sendToOwner;
    private long price;
    private String currency;
    private String mainImage;
    private List<String> images;
    private String adLink;
    private String nameWriter;
    private String CommentLink;
    private NotificationStatus status;
    private LocalDateTime dateBanNotification;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public String getUserProfileLink() {
        return userProfileLink;
    }

    public void setUserProfileLink(String userProfileLink) {
        this.userProfileLink = userProfileLink;
    }

    public String getSendToOwner() {
        return sendToOwner;
    }

    public void setSendToOwner(String sendToOwner) {
        this.sendToOwner = sendToOwner;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getAdLink() {
        return adLink;
    }

    public void setAdLink(String adLink) {
        this.adLink = adLink;
    }

    public String getNameWriter() {
        return nameWriter;
    }

    public void setNameWriter(String nameWriter) {
        this.nameWriter = nameWriter;
    }

    public String getCommentLink() {
        return CommentLink;
    }

    public void setCommentLink(String commentLink) {
        CommentLink = commentLink;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    public LocalDateTime getDateBanNotification() {
        return dateBanNotification;
    }

    public void setBanDate(LocalDateTime dateBanNotification) {
        this.dateBanNotification = dateBanNotification;
    }
}
