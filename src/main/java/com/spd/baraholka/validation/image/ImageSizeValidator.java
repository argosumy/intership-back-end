package com.spd.baraholka.validation.image;

import com.spd.baraholka.annotation.image.ImageSize;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class ImageSizeValidator implements ConstraintValidator<ImageSize, MultipartFile> {

    private static final int BYTES_IN_MB = 1024 * 1024;

    @Value("${user.avatar.maxsize.mb}")
    private int avatarMaxSizeInMB;

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        Objects.requireNonNull(file);
        return file.getSize() <= (long) avatarMaxSizeInMB * BYTES_IN_MB;
    }
}
