package com.spd.baraholka.image.service;

import com.spd.baraholka.image.persistance.entity.ImageResource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    ImageResource save(ImageResource imageResource);

    String uploadImage(String imageName, MultipartFile image);

    void saveAll(List<ImageResource> imageResources);

    List<ImageResource> getPrimary(List<Long> adIds);

    List<ImageResource> getAllByAdId(long adId);

    void deleteAllByAdId(long adId);

    void deleteImage(long imageId);

    void deleteImage(String imageUrl);

    String generateAvatarFileName(MultipartFile file);

    String getFileExtension(String filename);
}
