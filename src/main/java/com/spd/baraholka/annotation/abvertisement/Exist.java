package com.spd.baraholka.annotation.abvertisement;

import com.spd.baraholka.validation.advertisement.AdvertisementIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AdvertisementIdValidator.class)
public @interface Exist {

    String message() default "Baraholka advertisement status annotation validation";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
