package com.spd.baraholka.validation.advertisement;

import com.spd.baraholka.advertisement.persistance.entities.AdvertisementStatus;
import com.spd.baraholka.annotation.advertisement.EditedStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

import static com.spd.baraholka.advertisement.persistance.entities.AdvertisementStatus.*;

public class AdvertisementEditedStatusValidator implements ConstraintValidator<EditedStatus, AdvertisementStatus> {

    private static final Set<AdvertisementStatus> STATUSES = Set.of(ARCHIVED, DELETED, DRAFT, ACTIVE, DELAYED_PUBLICATION);

    @Override
    public boolean isValid(AdvertisementStatus value, ConstraintValidatorContext context) {
        return STATUSES.contains(value);
    }
}
