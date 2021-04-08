package com.spd.baraholka.image.persistance.repository;

import com.spd.baraholka.image.persistance.entity.*;

import java.util.*;

public interface ImageRepository {

    Image saveImage(Image image);

    void save(ImageResource imageResource);

    List<ImageResource> getPrimary(List<Integer> adIds);

    List<ImageResource> getAllByAdId(int adId);

    void deleteImage(int imageId);

    void setAttached(int imageId);

    List<Image> getUnattachedImages();

    Image getImage(int imageId);

    void deleteImageResourcesByAdId(int adId);

    boolean existImages(List<Integer> imageIds);
}
