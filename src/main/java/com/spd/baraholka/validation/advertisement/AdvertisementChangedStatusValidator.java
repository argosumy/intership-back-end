package com.spd.baraholka.validation.advertisement;

import com.spd.baraholka.advertisement.persistance.entities.AdvertisementStatus;
import com.spd.baraholka.annotation.advertisement.ChangedStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

import static com.spd.baraholka.advertisement.persistance.entities.AdvertisementStatus.ARCHIVED;
import static com.spd.baraholka.advertisement.persistance.entities.AdvertisementStatus.ON_HOLD;

public class AdvertisementChangedStatusValidator implements ConstraintValidator<ChangedStatus, AdvertisementStatus> {

    private static final Set<AdvertisementStatus> STATUSES = Set.of(ON_HOLD, ARCHIVED);

    @Override
    public boolean isValid(AdvertisementStatus value, ConstraintValidatorContext context) {
        return STATUSES.contains(value);
    }
}
