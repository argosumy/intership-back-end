package com.spduniversity.image.service;

import com.spduniversity.image.ImageResource;
import com.spduniversity.image.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ImageService {

    private final ImageRepository repository;
    private final AWS3Service aws3Service;

    private static final Comparator<ImageResource> COMPARATOR = Comparator.comparing(ImageResource::getPositionOrder);

    public ImageService(ImageRepository repository, AWS3Service aws3Service) {
        this.repository = repository;
        this.aws3Service = aws3Service;
    }

    public void saveAll(List<ImageResource> imageResources) {
        imageResources.forEach(imageResource -> {
            String imageUrl = aws3Service.uploadImage(imageResource);
            imageResource.setImageUrl(imageUrl);
        });

        repository.saveAll(imageResources);
    }

    public ImageResource save(ImageResource imageResource) {
        String imageUrl = aws3Service.uploadImage(imageResource);
        imageResource.setImageUrl(imageUrl);

        return repository.save(imageResource);
    }

    public Optional<ImageResource> getPrimary(long adId) {
        return repository.getPrimary(adId);
    }

    public List<ImageResource> getAllByAdId(Long adId) {
        List<ImageResource> imageResources = repository.getAllByAdId(adId);

        if (imageResources.isEmpty()) return imageResources;

        imageResources.sort(COMPARATOR);

        return imageResources;
    }

    public void deleteAllByAdId(long id) {
        Optional<ImageResource> imageResources = repository.getById(id);
        imageResources.stream().forEach(imageResource -> aws3Service.deleteImageFromS3Bucket(imageResource.getImageUrl()));
        repository.deleteAllByAdId(id);
    }

    public void delete(long id) {
        Optional<ImageResource> imageResourceContainer = repository.getById(id);

        if (imageResourceContainer.isPresent()) {
            ImageResource imageResource = imageResourceContainer.get();
            aws3Service.deleteImageFromS3Bucket(imageResource.getImageUrl());
            repository.delete(imageResource.getId());

            return;
        }

        throw new NoSuchElementException("Images with id " + id + " wasn't found.");
    }

}
