package com.spd.baraholka.image.controller.annotation;

import com.spd.baraholka.image.controller.dto.ImageResourceDto;
import com.spd.baraholka.image.persistance.repository.ImageRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;

public class ValidImageIdsConstrainValidator implements ConstraintValidator<ValidImageIds, List<ImageResourceDto>> {

    private final ImageRepository imageRepository;

    public ValidImageIdsConstrainValidator(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public boolean isValid(List<ImageResourceDto> value, ConstraintValidatorContext context) {
        List<Long> imageIds = value.stream().map(ImageResourceDto::getId).collect(Collectors.toList());

        return imageRepository.existImages(imageIds);
    }
}
