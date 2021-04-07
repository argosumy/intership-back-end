package com.spd.baraholka.validation.user;

import com.spd.baraholka.annotation.user.UserAdditionalResources;
import com.spd.baraholka.user.controller.dto.EditUserMainInfoDTO;
import com.spd.baraholka.user.controller.dto.UserAdditionalResourceDTO;
import com.spd.baraholka.user.service.UserProfileService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AdditionalResourceBelongToUserValidator implements ConstraintValidator<UserAdditionalResources, EditUserMainInfoDTO> {

    private final UserProfileService userService;

    public AdditionalResourceBelongToUserValidator(UserProfileService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(EditUserMainInfoDTO value, ConstraintValidatorContext context) {
        List<UserAdditionalResourceDTO> resources = value.getAdditionalContactResources();
        return resources != null && iBelongToUser(value, resources);
    }

    private boolean iBelongToUser(EditUserMainInfoDTO value, List<UserAdditionalResourceDTO> resources) {
        int userId = value.getUserId();
        Set<Integer> editResourcesId = resources.stream().map(UserAdditionalResourceDTO::getId).filter(resourceId -> resourceId != 0).collect(Collectors.toSet());
        Set<Integer> resourcesId = userService.getUserAdditionalResourcesId(userId);
        return editResourcesId.isEmpty() || resourcesId.containsAll(editResourcesId);
    }
}
