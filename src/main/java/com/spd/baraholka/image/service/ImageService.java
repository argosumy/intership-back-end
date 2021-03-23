package com.spd.baraholka.image.service;

import com.spd.baraholka.image.persistance.entity.ImageResource;

import java.util.List;

public interface ImageService {
    ImageResource save(ImageResource imageResource);

    void saveAll(List<ImageResource> imageResources);

    List<ImageResource> getPrimary(List<Long> adIds);

    List<ImageResource> getAllByAdId(long adId);

    void deleteAllByAdId(long adId);

    void deleteImage(long imageId);
}
