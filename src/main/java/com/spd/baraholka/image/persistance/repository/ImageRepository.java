package com.spd.baraholka.image.persistance.repository;

import com.spd.baraholka.image.persistance.entity.ImageResource;

import java.util.List;
import java.util.Optional;

public interface ImageRepository {
    long saveImageUrl(String imageUrl);

    void save(ImageResource imageResource);

    List<ImageResource> getPrimary(List<Long> adIds);

    List<ImageResource> getAllByAdId(long adId);

    Optional<ImageResource> getImageById(long imageId);

    void deleteImage(long imageId);
}
