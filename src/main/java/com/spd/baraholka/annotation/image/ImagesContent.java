package com.spd.baraholka.annotation.image;

import com.spd.baraholka.validation.image.ImageContentListValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ImageContentListValidator.class)
public @interface ImagesContent {

    String message() default "Some of provided files are not images or file extensions are not valid.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

