package com.spd.baraholka.advertisement.service;

import com.spd.baraholka.advertisement.persistance.AdvertisementStatus;
import com.spd.baraholka.advertisement.persistance.CurrencyType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class AdvertisementDTO {

    @NotNull
    private int advertisementId;

    @NotNull
    private int ownerId;

    @Size(max = 200)
    private String title;

    @NotNull
    private AdvertisementStatus status;

    @Positive
    private double price;

    @NotNull
    private LocalDateTime creationDate;

    @NotNull
    private LocalDateTime statusChangeDate;

    @Size(max = 20)
    private String city;

    private String description;
    private String category;
    private CurrencyType currency;
    private boolean discountAvailability;
    private LocalDateTime publicationDate;

    public int getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(int advertisementId) {
        this.advertisementId = advertisementId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }

    public boolean isDiscountAvailability() {
        return discountAvailability;
    }

    public void setDiscountAvailability(boolean discountAvailability) {
        this.discountAvailability = discountAvailability;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public AdvertisementStatus getStatus() {
        return status;
    }

    public void setStatus(AdvertisementStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public LocalDateTime getStatusChangeDate() {
        return statusChangeDate;
    }

    public void setStatusChangeDate(LocalDateTime statusChangeDate) {
        this.statusChangeDate = statusChangeDate;
    }
}
