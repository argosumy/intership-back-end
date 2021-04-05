package com.spd.baraholka.validation.image;

import com.spd.baraholka.annotation.image.ImageSize;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class ImageSizeValidator implements ConstraintValidator<ImageSize, MultipartFile> {

    private static final int BYTES_IN_MB = 1024 * 1024;

    private final Environment env;

    private int maxMB;

    public ImageSizeValidator(Environment env) {
        this.env = env;
    }

    @Override
    public void initialize(ImageSize constraintAnnotation) {
        this.maxMB = Integer.parseInt(env.resolvePlaceholders(constraintAnnotation.maxMB()));
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        Objects.requireNonNull(file);
        return file.getSize() <= (long) maxMB * BYTES_IN_MB;
    }
}
