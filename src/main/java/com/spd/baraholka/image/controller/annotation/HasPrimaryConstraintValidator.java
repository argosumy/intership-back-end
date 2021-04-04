package com.spd.baraholka.image.controller.annotation;

import com.spd.baraholka.image.controller.dto.ImageResourceDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class HasPrimaryConstraintValidator implements ConstraintValidator<HasPrimaryImage, List<ImageResourceDto>> {

    @Override
    public boolean isValid(List<ImageResourceDto> value, ConstraintValidatorContext context) {
        return value.stream().filter(ImageResourceDto::isPrimary).count() == 1;
    }
}