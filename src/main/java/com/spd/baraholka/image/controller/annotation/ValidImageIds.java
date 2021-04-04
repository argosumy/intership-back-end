package com.spd.baraholka.image.controller.annotation;

import javax.validation.*;
import java.lang.annotation.*;

@Constraint(validatedBy = ValidImageIdsConstrainValidator.class)
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidImageIds {
    String message() default "Collection has not existing images";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}