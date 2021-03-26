package com.spd.baraholka.advertisement.controller.dto;

import com.spd.baraholka.advertisement.persistance.entities.AdvertisementStatus;
import com.spd.baraholka.advertisement.persistance.entities.CurrencyType;

import java.time.LocalDateTime;

public class FullAdvertisementDTO {

    private int advertisementId;
    private String title;
    private AdvertisementStatus status;
    private double price;
    private String city;
    private String description;
    private CurrencyType currency;
    private boolean discountAvailability;
    private LocalDateTime publicationDate;
}
