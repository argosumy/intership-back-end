package com.spd.baraholka.advertisement.controller.dto;

import com.spd.baraholka.advertisement.persistance.entities.AdvertisementStatus;
import com.spd.baraholka.advertisement.persistance.entities.CurrencyType;
import com.spd.baraholka.annotation.advertisement.AdvertisementExist;
import com.spd.baraholka.annotation.advertisement.EditedStatus;
import com.spd.baraholka.annotation.advertisement.PositivePrice;
import com.spd.baraholka.characteristic.controller.dto.CharacteristicDTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

public class EditedAdvertisementDTO {

    @NotNull
    @Min(1)
    @AdvertisementExist
    private int advertisementId;

    @Size(max = 200)
    private String title;

    @EditedStatus
    private AdvertisementStatus status;

    @PositivePrice
    private double price;

    @Size(max = 25)
    private String city;

    @Size(max = 2000)
    private String description;

    @NotNull
    private CurrencyType currency;

    @NotNull
    private boolean discountAvailability;

    private int ownerId;

    private LocalDateTime publicationDate;
    private List<CharacteristicDTO> characteristics;

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

    public List<CharacteristicDTO> getCharacteristics() {
        return characteristics;
    }

    public void setAdvertisementId(int advertisementId) {
        this.advertisementId = advertisementId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(AdvertisementStatus status) {
        this.status = status;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void setCharacteristics(List<CharacteristicDTO> characteristics) {
        this.characteristics = characteristics;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
