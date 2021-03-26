package com.spd.baraholka.validation.advertisement;

import com.spd.baraholka.advertisement.persistance.entities.AdvertisementStatus;
import com.spd.baraholka.annotation.advertisement.InitialStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

import static com.spd.baraholka.advertisement.persistance.entities.AdvertisementStatus.*;

public class AdvertisementInitialStatusValidator implements ConstraintValidator<InitialStatus, AdvertisementStatus> {

    @Override
    public boolean isValid(AdvertisementStatus value, ConstraintValidatorContext context) {
        List<AdvertisementStatus> statuses = List.of(DRAFT, ACTIVE, DELAYED_PUBLICATION);
        return statuses.contains(value);
    }
}
