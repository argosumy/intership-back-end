package com.spd.baraholka.annotation.image;

import com.spd.baraholka.validation.image.ImageContentValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ImageContentValidator.class)
public @interface ImageContent {

    String message() default "Provided file is not an image or file extension does not correspond to an image.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

