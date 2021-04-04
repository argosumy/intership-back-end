package com.spd.baraholka.image.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.spd.baraholka.config.exceptions.MultipartFileConversionFailureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Service
public class AWS3ServiceImpl implements AWS3Service {

    private final AmazonS3 amazonS3Client;

    private final String amazonDomain;

    private final String bucketName;

    @Autowired
    public AWS3ServiceImpl(@Value("${amazon.domain}") String amazonDomain,
                           @Value("${amazonProperties.bucketName}") String bucketName,
                           AmazonS3 amazonS3Client) {
        this.amazonDomain = amazonDomain;
        this.bucketName = bucketName;
        this.amazonS3Client = amazonS3Client;
    }

    @Override
    public String uploadImage(String fileName, MultipartFile image) {
        File file = convertMultiPartFileToFile(image);
        try {
            amazonS3Client.putObject(
                    new PutObjectRequest(bucketName, fileName, file)
            );
        } finally {
            file.delete();
        }
        return amazonDomain + bucketName + "/" + fileName;
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
