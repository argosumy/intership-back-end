package com.spd.baraholka.validation.image;

import com.spd.baraholka.annotation.image.ImageContent;
import com.spd.baraholka.image.service.ImageService;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.Set;

public class ImageContentValidator implements ConstraintValidator<ImageContent, MultipartFile> {

    private static final String IMAGE_MIME_PREFIX = "image/";

    private final ImageService imageService;

    private static final Set<String> IMAGE_FILE_EXTENSIONS = Set.of("jpg", "jpeg", "png", "gif", "tif", "bmp", "svg");

    public ImageContentValidator(ImageService imageService) {
        this.imageService = imageService;
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        Objects.requireNonNull(file);
        return isImageContentType(file) && isImageFileExtension(file);
    }

    private boolean isImageContentType(MultipartFile file) {
        Objects.requireNonNull(file);
        String mimetype = Objects.requireNonNull(file.getContentType());
        return mimetype.startsWith(IMAGE_MIME_PREFIX);
    }

    private boolean isImageFileExtension(MultipartFile file) {
        Objects.requireNonNull(file);
        String filename = file.getOriginalFilename();
        String fileExtension = imageService.getFileExtension(filename).toLowerCase();
        return IMAGE_FILE_EXTENSIONS.contains(fileExtension);
    }
}
