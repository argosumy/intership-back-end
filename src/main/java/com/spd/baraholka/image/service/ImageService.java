package com.spd.baraholka.image.service;

import com.spd.baraholka.image.persistance.entity.*;
import org.springframework.web.multipart.*;

import java.util.*;

public interface ImageService {
    ImageResource save(ImageResource imageResource);

    void saveImageResources(long adId, List<ImageResource> imageResources);

    Image uploadImage(long adId, MultipartFile image);

    List<Image> getAllUnattached();

    String uploadImage(String imageName, MultipartFile image);

    void saveAll(List<ImageResource> imageResources);

    List<ImageResource> getPrimary(List<Integer> adIds);

    List<ImageResource> getAllByAdId(long adId);

    void deleteAllByAdId(long adId);

    void deleteImage(long imageId);

    void deleteImage(String imageUrl);

    String generateAvatarFileName(MultipartFile file);

    String getFileExtension(String filename);
}
