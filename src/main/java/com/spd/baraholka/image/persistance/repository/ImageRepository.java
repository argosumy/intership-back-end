package com.spd.baraholka.image.persistance.repository;

import com.spd.baraholka.image.persistance.entity.*;

import java.util.*;

public interface ImageRepository {

    Image saveImage(Image image);

    void save(ImageResource imageResource);

    List<ImageResource> getPrimary(List<Long> adIds);

    List<ImageResource> getAllByAdId(long adId);

    Optional<ImageResource> getImageById(long imageId);

    void deleteImage(long imageId);

    void updateImageResource(ImageResource imageResource);

    void setAttached(long imageId);

    List<Image> getUnattachedImages();

    Image getImage(long imageId);

    void deleteImageResourcesByAdId(long adId);

    boolean existImages(List<Long> imageIds);
}
