package com.spd.baraholka.annotation.user;


import com.spd.baraholka.validation.user.AdditionalResourceBelongToUserValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AdditionalResourceBelongToUserValidator.class)
public @interface BelongToUser {

    String message() default "Additional resource does not belong to user";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
