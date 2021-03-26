package com.spd.baraholka.validation.advertisement;

import com.spd.baraholka.advertisement.persistance.entities.AdvertisementStatus;
import com.spd.baraholka.annotation.abvertisement.InitialStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

import static com.spd.baraholka.advertisement.persistance.entities.AdvertisementStatus.*;

public class AdvertisementInitialStatusValidator implements ConstraintValidator<InitialStatus, AdvertisementStatus> {

    @Override
    public boolean isValid(AdvertisementStatus value, ConstraintValidatorContext context) {
        return isAllowed(value);
    }

    private boolean isAllowed(AdvertisementStatus status) {
        List<AdvertisementStatus> statuses = List.of(DRAFT, ACTIVE, DELAYED_PUBLICATION);
        return statuses.contains(status);
    }
}
