package com.spd.baraholka.advertisements.services;

import com.spd.baraholka.advertisements.persistance.AdvertisementStatus;
import com.spd.baraholka.advertisements.persistance.CurrencyType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class AdvertisementDTO {

    @NotNull
    private final int advertisementId;

    @NotNull
    private final int ownerId;

    @Size(max = 200)
    private final String title;

    @NotNull
    private AdvertisementStatus status;

    @Positive
    private final double price;

    @NotNull
    private final LocalDateTime creationDate;

    @NotNull
    private final LocalDateTime statusChangeDate;

    @Size(max = 20)
    private final String city;

    private final String description;
    private final String category;
    private final CurrencyType currency;
    private final boolean discountAvailability;
    private final LocalDateTime publicationDate;

    public AdvertisementDTO(int advertisementId, int ownerId, String title, String description, String category, double price,
                         CurrencyType currency, boolean discountAvailability, String city, AdvertisementStatus status, LocalDateTime creationDate,
                         LocalDateTime publicationDate, LocalDateTime statusChangeDate) {
        this.advertisementId = advertisementId;
        this.ownerId = ownerId;
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
        this.currency = currency;
        this.discountAvailability = discountAvailability;
        this.city = city;
        this.status = status;
        this.creationDate = creationDate;
        this.publicationDate = publicationDate;
        this.statusChangeDate = statusChangeDate;
    }

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
}
