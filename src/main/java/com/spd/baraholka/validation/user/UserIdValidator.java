package com.spd.baraholka.validation.user;

import com.spd.baraholka.annotation.user.UserExist;
import com.spd.baraholka.user.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserIdValidator implements ConstraintValidator<UserExist, Integer> {

    private final UserService userService;

    public UserIdValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return userService.isUserExist(value);
    }
}
