package com.spd.baraholka.validation.user;

import com.spd.baraholka.annotation.user.BelongToUser;
import com.spd.baraholka.user.controller.dto.UserAdditionalResourceDTO;
import com.spd.baraholka.user.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class AdditionalResourceBelongToUserValidator implements ConstraintValidator<BelongToUser, UserAdditionalResourceDTO> {

    private final UserService userService;

    public AdditionalResourceBelongToUserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(UserAdditionalResourceDTO value, ConstraintValidatorContext context) {
        int id = value.getId();
        int userId = value.getUserId();
        if (id != 0) {
            List<Integer> additionalResourcesId = userService.getUserAdditionalResourcesId(userId);
            return additionalResourcesId.stream().anyMatch(integer -> integer == id);
        } else {
            return true;
        }
    }
}
