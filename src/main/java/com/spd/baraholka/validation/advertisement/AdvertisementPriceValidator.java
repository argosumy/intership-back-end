package com.spd.baraholka.validation.advertisement;

import com.spd.baraholka.annotation.advertisement.PositivePrice;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AdvertisementPriceValidator implements ConstraintValidator<PositivePrice, Double> {

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return value >= 0;
    }
}
