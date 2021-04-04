package com.spd.baraholka.image.controller.annotation;

import javax.validation.*;
import java.lang.annotation.*;

@Constraint(validatedBy = UniquePositionsConstrainValidator.class)
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidatePositions {
    String message() default "Positions numbers must be sequential in range form 1 to 10; duplicates are forbidden.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
