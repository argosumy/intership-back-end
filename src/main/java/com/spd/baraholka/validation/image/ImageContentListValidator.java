package com.spd.baraholka.validation.image;

import com.spd.baraholka.annotation.image.ImagesContent;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Objects;

public class ImageContentListValidator implements ConstraintValidator<ImagesContent, List<MultipartFile>> {

    private final ImageContentValidator imageContentValidator;

    public ImageContentListValidator(ImageContentValidator imageContentValidator) {
        this.imageContentValidator = imageContentValidator;
    }

    @Override
    public boolean isValid(List<MultipartFile> files, ConstraintValidatorContext context) {
        Objects.requireNonNull(files);
        boolean isValid = true;
        for (MultipartFile file : files) {
            isValid = isValid && imageContentValidator.isValid(file, context);
        }
        return isValid;
    }
}
