package com.spduniversity.image.service;

import com.spduniversity.image.ImageResource;
import com.spduniversity.image.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ImageService {

    private final ImageRepository repository;
    private final AWS3Service aws3Service;

    private static final Comparator<ImageResource> COMPARATOR = Comparator.comparing(ImageResource::getPosition);

    public ImageService(ImageRepository repository, AWS3Service aws3Service) {
        this.repository = repository;
        this.aws3Service = aws3Service;
    }

    public ImageResource save(ImageResource imageResource) {
        uploadImage(imageResource);

        repository.save(imageResource);

        return imageResource;
    }

    public void saveAll(List<ImageResource> imageResources) {
        imageResources.forEach(this::save);
    }

    public List<ImageResource> getPrimary(List<Long> adIds) {
        return repository.getPrimary(adIds);
    }

    public List<ImageResource> getAllByAdId(long adId) {
        List<ImageResource> imageResources = repository.getAllByAdId(adId);

        if (imageResources.isEmpty()) return imageResources;

        imageResources.sort(COMPARATOR);

        return imageResources;
    }

    public void deleteAllByAdId(long adId) {
        List<ImageResource> imageResources = repository.getAllByAdId(adId);
        imageResources.forEach(imageResource -> {
            aws3Service.deleteImageFromS3Bucket(imageResource.getImageUrl());
            repository.deleteImage(imageResource.getId());
        });
    }

    public void deleteImage(long imageId) {
        Optional<ImageResource> imageResourceContainer = repository.getImageById(imageId);

        if (imageResourceContainer.isEmpty())
            throw new NoSuchElementException("Images with id " + imageId + " wasn't found.");

        ImageResource imageResource = imageResourceContainer.get();
        aws3Service.deleteImageFromS3Bucket(imageResource.getImageUrl());
        repository.deleteImage(imageResource.getId());
    }

    private void uploadImage(ImageResource imageResource) {
        String imageUrl = aws3Service.uploadImage(imageResource);
        long imageId = repository.saveImageUrl(imageUrl);
        imageResource.setId(imageId);
        imageResource.setImageUrl(imageUrl);
    }
}
