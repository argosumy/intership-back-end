package com.spd.baraholka.advertisement.controller.dto;

import com.spd.baraholka.advertisement.persistance.entities.AdvertisementStatus;
import com.spd.baraholka.advertisement.persistance.entities.CurrencyType;
import com.spd.baraholka.annotation.abvertisement.InitialStatus;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class InitialAdvertisementDTO {

    @Size(max = 200)
    private String title;

    @NotBlank
    @InitialStatus
    private AdvertisementStatus status;

    @Positive
    private double price;

    @Size(max = 25)
    private String city;

    @Size(max = 2000)
    private String description;

    @NotBlank
    private CurrencyType currency;

    @NotBlank
    private boolean discountAvailability;

    @FutureOrPresent
    private LocalDateTime publicationDate;
}
