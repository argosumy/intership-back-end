package com.spd.baraholka.validation.advertisement;

import com.spd.baraholka.annotation.abvertisement.PresentOrFutureDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class PresentOrFutureDateValidator implements ConstraintValidator<PresentOrFutureDate, LocalDateTime> {

    private static final int AVAILABLE_MINUTES_MISS = 2;

    @Override
    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
        LocalDateTime now = LocalDateTime.now().minusMinutes(AVAILABLE_MINUTES_MISS);
        return !value.isBefore(now);
    }
}
