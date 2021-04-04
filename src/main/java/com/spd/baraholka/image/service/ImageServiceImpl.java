package com.spd.baraholka.image.service;

import com.spd.baraholka.image.persistance.entity.ImageResource;
import com.spd.baraholka.image.persistance.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class ImageServiceImpl implements ImageService {

    private static final String AVATAR_PREFIX = "avatar_";

    private final ImageRepository repository;

    private final AWS3Service aws3Service;

    private static final Comparator<ImageResource> COMPARATOR = Comparator.comparing(ImageResource::getPosition);

    public ImageServiceImpl(ImageRepository repository, AWS3ServiceImpl aws3Service) {
        this.repository = repository;
        this.aws3Service = aws3Service;
    }

    @Override
    public ImageResource save(ImageResource imageResource) {
        String imageName = generateFileName(imageResource);

        String imageUrl = uploadImage(imageName, imageResource.getImage());

        long imageId = repository.saveImageUrl(imageUrl);

        imageResource.setId(imageId);
        imageResource.setImageUrl(imageUrl);

        repository.save(imageResource);

        return imageResource;
    }

    @Override
    public String uploadImage(String imageName, MultipartFile image) {
        return aws3Service.uploadImage(imageName, image);
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

    @Override
    public void deleteImage(long imageId) {
        Optional<ImageResource> imageResourceContainer = repository.getImageById(imageId);

        if (imageResourceContainer.isEmpty()) {
            throw new NoSuchElementException("Images with id " + imageId + " wasn't found.");
        }

        ImageResource imageResource = imageResourceContainer.get();
        aws3Service.deleteImageFromS3Bucket(imageResource.getImageUrl());
        repository.deleteImage(imageResource.getId());
    }

    private String generateFileName(ImageResource imageResource) {
        long adId = imageResource.getAdId();
        MultipartFile multiPart = imageResource.getImage();
        String originalFileName = generateFileName(multiPart);

        return String.format("ads/%s/%s-%s", adId, new Date().getTime(), originalFileName);
    }

    private String generateFileName(MultipartFile file) {
        return Objects.requireNonNull(file.getOriginalFilename()).replace(" ", "_");
    }

    @Override
    public String generateAvatarFileName(int userId, MultipartFile file) {
        Objects.requireNonNull(file);
        String fileExtension = getFileExtension(file.getOriginalFilename());
        return AVATAR_PREFIX + userId + fileExtension;
    }

    private String getFileExtension(String filename) {
        String delimiter = "\\.";
        Objects.requireNonNull(filename);
        String[] fileNameParts = filename.split(delimiter);
        if (fileNameParts.length < 2) {
            return ".jpg";
        }
        return "." + fileNameParts[fileNameParts.length - 1];
    }
}
