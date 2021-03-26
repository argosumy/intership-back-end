package com.spd.baraholka.advertisement.controller.dto;

import com.spd.baraholka.advertisement.persistance.entities.AdvertisementStatus;
import com.spd.baraholka.advertisement.persistance.entities.CurrencyType;
import com.spd.baraholka.annotation.abvertisement.EditedStatus;
import com.spd.baraholka.annotation.abvertisement.PresentOrFutureDate;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class EditedAdvertisementDTO {

    @NotNull
    @Min(1)
    private int advertisementId;

    @Size(max = 200)
    private String title;

    @EditedStatus
    private AdvertisementStatus status;

    @Positive
    private double price;

    @Size(max = 25)
    private String city;

    @Size(max = 2000)
    private String description;

    @NotNull
    private CurrencyType currency;

    @NotNull
    private boolean discountAvailability;

    @PresentOrFutureDate
    private LocalDateTime publicationDate;

    public int getAdvertisementId() {
        return advertisementId;
    }

    public String getTitle() {
        return title;
    }

    public AdvertisementStatus getStatus() {
        return status;
    }

    public double getPrice() {
        return price;
    }

    public String getCity() {
        return city;
    }

    public String getDescription() {
        return description;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public boolean isDiscountAvailability() {
        return discountAvailability;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }
}
