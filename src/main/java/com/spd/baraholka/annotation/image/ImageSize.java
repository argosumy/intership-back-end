package com.spd.baraholka.annotation.image;

import com.spd.baraholka.validation.image.ImageSizeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ImageSizeValidator.class)
public @interface ImageSize {
    String message() default "Image too large";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
