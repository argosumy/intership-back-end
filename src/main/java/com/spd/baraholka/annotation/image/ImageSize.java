package com.spd.baraholka.annotation.image;

import com.spd.baraholka.validation.image.ImageSizeValidator;
import org.springframework.beans.factory.annotation.Value;

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

    @Value("${user.avatar.maxsize.mb}")
    int max() default 2;

    String message() default "Avatar image is too large and exceeds its maximum permitted size of {max}.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
