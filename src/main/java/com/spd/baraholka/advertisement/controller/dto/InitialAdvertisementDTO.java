package com.spd.baraholka.advertisement.controller.dto;

import com.spd.baraholka.advertisement.persistance.entities.AdvertisementStatus;
import com.spd.baraholka.advertisement.persistance.entities.CurrencyType;
import com.spd.baraholka.annotation.advertisement.InitialStatus;
import com.spd.baraholka.annotation.advertisement.PositivePrice;
import com.spd.baraholka.annotation.advertisement.PresentOrFutureDate;
import com.spd.baraholka.annotation.user.UserExist;
import com.spd.baraholka.characteristic.controller.dto.CharacteristicDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

public class InitialAdvertisementDTO {

    @NotNull
    private String category;

    @Size(max = 200)
    private String title;

    @InitialStatus
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

    @PresentOrFutureDate
    private LocalDateTime publicationDate;

    private int ownerId;

    private List<CharacteristicDTO> characteristics;

    public String getCategory() {
        return category;
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
