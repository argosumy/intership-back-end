package com.spd.baraholka.image.service;

import com.spd.baraholka.image.persistance.entity.*;
import org.springframework.web.multipart.*;

import java.util.*;

public interface ImageService {
    ImageResource save(ImageResource imageResource);

    void saveImageResources(int adId, List<ImageResource> imageResources);

    Image uploadImage(int adId, MultipartFile image);

    List<Image> getAllUnattached();

    String uploadImage(String imageName, MultipartFile image);

    void saveAll(List<ImageResource> imageResources);

    List<ImageResource> getPrimary(List<Integer> adIds);

    List<ImageResource> getAllByAdId(int adId);

    void deleteAllByAdId(int adId);

    void deleteImage(int imageId);

    void deleteImage(String imageUrl);

    String generateAvatarFileName(MultipartFile file);

    String getFileExtension(String filename);
}
