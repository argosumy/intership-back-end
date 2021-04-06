package com.spd.baraholka.image.service;

import com.spd.baraholka.image.persistance.entity.*;
import com.spd.baraholka.image.persistance.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;

import java.time.*;
import java.util.*;
import java.util.stream.*;

@Service
public class ImageServiceImpl implements ImageService {

    private final String amazonDomain;

    private final ImageRepository repository;

    private final AWS3Service aws3Service;

    private final String bucketName;

    private static final Comparator<ImageResource> COMPARATOR = Comparator.comparing(ImageResource::getPosition);

    public ImageServiceImpl(ImageRepository repository,
                            AWS3ServiceImpl aws3Service,
                            @Value("${amazon.domain}") String amazonDomain,
                            @Value("${amazonProperties.bucketName}") String bucketName) {
        this.repository = repository;
        this.aws3Service = aws3Service;
        this.amazonDomain = amazonDomain;
        this.bucketName = bucketName;
    }

    @Override
    public ImageResource save(ImageResource imageResource) {
        String imageName = generateFileName(imageResource);
        String imageUrl = amazonDomain + bucketName + "/" + imageName;

        aws3Service.uploadImage(imageName, imageResource.getImage());

        Image image = new Image();
        image.setUrl(imageUrl);
        image.setAttached(true);
        image.setUploadedAt(LocalDateTime.now());

        repository.saveImage(image);

        imageResource.setId(image.getId());
        imageResource.setImageUrl(image.getUrl());

        repository.save(imageResource);

        return imageResource;
    }

    @Override
    public void saveImageResources(long adId, List<ImageResource> imageResources) {
        List<ImageResource> attachedImages = repository.getAllByAdId(adId);
        if (!attachedImages.isEmpty()) {
            repository.deleteImageResourcesByAdId(adId);
            deleteImagesDifferenceFromS3(imageResources, attachedImages);
        }

        imageResources.forEach(imageResource -> {
            repository.setAttached(imageResource.getId());
            repository.save(imageResource);
        });
    }

    @Override
    public Image uploadImage(long adId, MultipartFile file) {
        String imageName = generateFileName(adId, file);
        String imageUrl = amazonDomain + bucketName + "/" + imageName;

        aws3Service.uploadImage(imageName, file);

        Image image = new Image();
        image.setUrl(imageUrl);
        image.setAttached(false);
        image.setUploadedAt(LocalDateTime.now());

        return repository.saveImage(image);
    }

    @Override
    public List<Image> getAllUnattached() {
        return repository.getUnattachedImages();
    }

    @Override
    public void saveAll(List<ImageResource> imageResources) {
        imageResources.forEach(this::save);
    }

    @Override
    public List<ImageResource> getPrimary(List<Long> adIds) {
        return repository.getPrimary(adIds);
    }

    @Override
    public List<ImageResource> getAllByAdId(long adId) {
        List<ImageResource> imageResources = repository.getAllByAdId(adId);

        if (imageResources.isEmpty()) {
            return imageResources;
        }

        imageResources.sort(COMPARATOR);

        return imageResources;
    }

    @Override
    public void deleteAllByAdId(long adId) {
        List<ImageResource> imageResources = repository.getAllByAdId(adId);
        imageResources.forEach(imageResource -> {
            aws3Service.deleteImageFromS3Bucket(imageResource.getImageUrl());
            repository.deleteImage(imageResource.getId());
        });
    }

    public void deleteImage(long imageId) {
        Image image = repository.getImage(imageId);
        aws3Service.deleteImageFromS3Bucket(image.getUrl());
        repository.deleteImage(imageId);
    }

    private void deleteImagesDifferenceFromS3(List<ImageResource> imageResources, List<ImageResource> attachedImages) {
        Set<Long> attachedImagesIds = attachedImages.stream().map(ImageResource::getId).collect(Collectors.toSet());
        Set<Long> toBeSavedImagesIds = imageResources.stream().map(ImageResource::getId).collect(Collectors.toSet());
        List<Long> difference = attachedImagesIds
                .stream()
                .filter(imageResource -> !toBeSavedImagesIds.contains(imageResource))
                .collect(Collectors.toList());

        difference.forEach(imageId -> {
            Image image = repository.getImage(imageId);
            aws3Service.deleteImageFromS3Bucket(image.getUrl());
            repository.deleteImage(imageId);
        });
    }

    private String generateFileName(ImageResource imageResource) {
        long adId = imageResource.getAdId();
        MultipartFile multiPart = imageResource.getImage();
        String originalFileName = Objects.requireNonNull(multiPart.getOriginalFilename()).replace(" ", "_");

        return String.format("ads/%s/%s-%s", adId, new Date().getTime(), originalFileName);
    }

    private String generateFileName(long adId, MultipartFile multiPart) {
        String originalFileName = Objects.requireNonNull(multiPart.getOriginalFilename()).replace(" ", "_");

        return String.format("ads/%s/%s-%s", adId, new Date().getTime(), originalFileName);
    }
}
