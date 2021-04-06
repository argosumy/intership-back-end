package com.spd.baraholka.image.controller.annotation;

import javax.validation.*;
import java.lang.annotation.*;

@Constraint(validatedBy = HasPrimaryConstraintValidator.class)
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface HasPrimaryImage {
    String message() default "Collection must have one element with property 'primary = true'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
