package com.spd.baraholka.validation.user;

import com.spd.baraholka.annotation.user.BelongToUser;
import com.spd.baraholka.user.controller.dto.UserAdditionalResourceDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AdditionalResourceBelongToUserValidator implements ConstraintValidator<BelongToUser, UserAdditionalResourceDTO> {

    @Override
    public boolean isValid(UserAdditionalResourceDTO value, ConstraintValidatorContext context) {
        return true; //TODO add validation by belonging to user
    }
}
