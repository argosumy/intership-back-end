package com.spd.baraholka.image.controller.annotation;

import com.spd.baraholka.advertisement.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import javax.validation.*;

@Component
public class IsAdExistingConstraintValidator implements ConstraintValidator<IsAdExisting, Integer> {
    private final AdvertisementService adService;

    @Autowired
    public IsAdExistingConstraintValidator(AdvertisementService adService) {
        this.adService = adService;
    }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext context) {
        // Must be fixed. Long type value is down casted here to int.
        return adService.isAdvertisementExist(id);
    }
}
