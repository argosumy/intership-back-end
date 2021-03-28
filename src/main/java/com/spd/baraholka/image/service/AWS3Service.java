package com.spd.baraholka.image.service;

import org.springframework.web.multipart.MultipartFile;

public interface AWS3Service {
    void uploadImage(String fileName, MultipartFile image);

    boolean deleteImageFromS3Bucket(String fileUrl);
}