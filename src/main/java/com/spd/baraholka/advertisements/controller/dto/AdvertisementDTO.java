package com.spd.baraholka.advertisements.controller.dto;

import com.spd.baraholka.advertisements.persistance.entities.AdvertisementStatus;
import com.spd.baraholka.advertisements.persistance.entities.CurrencyType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class AdvertisementDTO {

    private int advertisementId;
    private int ownerId;
    @Size(max = 200)
    private String title;
    @NotNull
    private AdvertisementStatus status;
    @Positive
    private double price;
    @PastOrPresent
    private LocalDateTime creationDate;
    @PastOrPresent
    private LocalDateTime statusChangeDate;
    @Size(max = 20)
    private String city;
    @NotNull
    private String description;
    @NotNull
    private String category;
    @NotNull
    private CurrencyType currency;
    private boolean discountAvailability;
    @NotNull
    private LocalDateTime publicationDate;

    public int getAdvertisementId() {
        return advertisementId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public boolean isDiscountAvailability() {
        return discountAvailability;
    }

    public String getCity() {
        return city;
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

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public LocalDateTime getStatusChangeDate() {
        return statusChangeDate;
    }

    public void setAdvertisementId(int advertisementId) {
        this.advertisementId = advertisementId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setStatusChangeDate(LocalDateTime statusChangeDate) {
        this.statusChangeDate = statusChangeDate;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }

    public void setDiscountAvailability(boolean discountAvailability) {
        this.discountAvailability = discountAvailability;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }
}
