package com.spd.baraholka.image.controller.annotation;

import javax.validation.*;
import java.lang.annotation.*;

@Constraint(validatedBy = IsAdExistingConstraintValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsAdExisting {
    String message() default "Can't bind image(s) to a not existing advertisement.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
