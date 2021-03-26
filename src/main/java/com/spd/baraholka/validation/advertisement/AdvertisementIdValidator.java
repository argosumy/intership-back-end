package com.spd.baraholka.validation.advertisement;

import com.spd.baraholka.advertisement.service.AdvertisementService;
import com.spd.baraholka.annotation.abvertisement.AdvertisementExist;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AdvertisementIdValidator implements ConstraintValidator<AdvertisementExist, Integer> {

    private final AdvertisementService advertisementService;

    public AdvertisementIdValidator(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return advertisementService.isAdvertisementExist(value);
    }
}
