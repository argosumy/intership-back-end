package com.spd.baraholka.image.service;

import com.amazonaws.services.s3.*;
import com.amazonaws.services.s3.model.*;
import com.spd.baraholka.config.exceptions.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;

import java.io.*;
import java.util.*;

@Service
public class AWS3ServiceImpl implements AWS3Service {

    private final AmazonS3 amazonS3Client;

    private final String bucketName;

    @Autowired
    public AWS3ServiceImpl(@Value("${amazonProperties.bucketName}") String bucketName, AmazonS3 amazonS3Client) {
        this.bucketName = bucketName;
        this.amazonS3Client = amazonS3Client;
    }

    @Override
    public void uploadImage(String fileName, MultipartFile image) {
        File file = convertMultiPartFileToFile(image);
        try {
            amazonS3Client.putObject(
                    new PutObjectRequest(bucketName, fileName, file)
            );
        } finally {
            file.delete();
        }
    }

    @Override
    public boolean deleteImageFromS3Bucket(String fileUrl) {
        String objectName = fileUrl.substring(fileUrl.lastIndexOf("ads/"));

        if (amazonS3Client.doesObjectExist(bucketName, objectName)) {
            amazonS3Client.deleteObject(new DeleteObjectRequest(bucketName, objectName));

            return true;
        }

        return false;
    }

    private File convertMultiPartFileToFile(MultipartFile multipartFile) {
        final File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));

        try (FileOutputStream outputStream = new FileOutputStream(file);
              BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream)) {
            bufferedOutputStream.write(multipartFile.getBytes());
            bufferedOutputStream.flush();
        } catch (IOException ex) {
            String message = "Failed to convert the Multipart file with name of " + multipartFile.getOriginalFilename() +
                    " into a regular file before uploading to s3 bucket.";

            throw new MultipartFileConversionFailureException(message);
        }

        return file;
    }
}
