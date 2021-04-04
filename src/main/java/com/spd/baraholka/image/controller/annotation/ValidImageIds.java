package com.spd.baraholka.image.controller.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidImageIdsConstrainValidator.class)
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidImageIds {
    String message() default "Collection has not existing images";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}