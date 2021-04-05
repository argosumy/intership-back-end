package com.spd.baraholka.image.service;

import com.spd.baraholka.image.persistance.entity.*;
import com.spd.baraholka.image.persistance.repository.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;
import java.util.stream.*;

@Service
public class ImageCleaningServiceImpl implements ImageCleaningService {

    // Unattached to an ad images keep time (in minutes)
    public static final int KEEP_TIME = 60;
    // millisecond * seconds * minutes
    public static final int INITIAL_DELAY = 1000 * 60 * 60 * 2;
    public static final int EXECUTION_PERIOD = 1000 * 60 * 60 * 2;

    private final ImageRepository repository;

    private final AWS3Service aws3Service;

    public ImageCleaningServiceImpl(ImageRepository repository, AWS3Service aws3Service) {
        this.repository = repository;
        this.aws3Service = aws3Service;
    }

    @Override
    @Scheduled(fixedDelay = EXECUTION_PERIOD, initialDelay = INITIAL_DELAY)
    public void removeUnattachedImages() {
        List<Image> unattachedImages = repository.getUnattachedImages();
        List<Image> candidatesForRemoving = filterForDeletion(unattachedImages);

        candidatesForRemoving.forEach(image -> {
            aws3Service.deleteImageFromS3Bucket(image.getUrl());
            repository.deleteImage(image.getId());
        });
    }

    private List<Image> filterForDeletion(List<Image> images) {
        LocalDateTime now = LocalDateTime.now();

        return images
                .stream()
                .filter(image -> isKeepTimeExceeded(image.getUploadedAt(), now))
                .collect(Collectors.toList());
    }

    private boolean isKeepTimeExceeded(LocalDateTime uploadedAt, LocalDateTime now) {
        return Duration.between(uploadedAt, now).toMinutes() >= KEEP_TIME;
    }
}