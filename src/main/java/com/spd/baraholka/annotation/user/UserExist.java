package com.spd.baraholka.annotation.user;

import com.spd.baraholka.validation.user.UserIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserIdValidator.class)
public @interface UserExist {

    String message() default "User not exist in system";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
