package com.spd.baraholka.validation.advertisement;

import com.spd.baraholka.advertisement.persistance.entities.AdvertisementStatus;
import com.spd.baraholka.annotation.advertisement.InitialStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

import static com.spd.baraholka.advertisement.persistance.entities.AdvertisementStatus.*;

public class AdvertisementInitialStatusValidator implements ConstraintValidator<InitialStatus, AdvertisementStatus> {

    private static final Set<AdvertisementStatus> STATUSES = Set.of(DRAFT, ACTIVE, DELAYED_PUBLICATION);

    @Override
    public boolean isValid(AdvertisementStatus value, ConstraintValidatorContext context) {
        return STATUSES.contains(value);
    }
}
