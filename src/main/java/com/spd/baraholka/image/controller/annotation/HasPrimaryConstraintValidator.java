package com.spd.baraholka.image.controller.annotation;

import com.spd.baraholka.image.controller.dto.*;

import javax.validation.*;
import java.util.*;

public class HasPrimaryConstraintValidator implements ConstraintValidator<HasPrimaryImage, List<ImageResourceDto>> {

    @Override
    public boolean isValid(List<ImageResourceDto> value, ConstraintValidatorContext context) {
        return value.stream().filter(ImageResourceDto::isPrimary).count() == 1;
    }
}