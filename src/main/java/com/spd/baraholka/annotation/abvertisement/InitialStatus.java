package com.spd.baraholka.annotation.abvertisement;

import com.spd.baraholka.validation.advertisement.AdvertisementInitialStatusValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AdvertisementInitialStatusValidator.class)
public @interface InitialStatus {

    String message() default "Baraholka advertisement status annotation validation";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
