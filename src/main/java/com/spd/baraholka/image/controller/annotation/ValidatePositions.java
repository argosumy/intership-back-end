package com.spd.baraholka.image.controller.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniquePositionsConstrainValidator.class)
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidatePositions {
    String message() default "Positions numbers must be sequential in range form 1 to 10; duplicates are forbidden.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
