package com.spd.baraholka.annotation.advertisement;

import com.spd.baraholka.validation.advertisement.AdvertisementPriceValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AdvertisementPriceValidator.class)
public @interface PositivePrice {

    String message() default "Price can not be lover then 0";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
