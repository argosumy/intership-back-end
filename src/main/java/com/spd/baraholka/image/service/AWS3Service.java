package com.spd.baraholka.image.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.spd.baraholka.image.ImageResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@Service
public class AWS3Service {

    private static final String AMAZON_DOMAIN = "https://s3-eu-west-1.amazonaws.com/";

    private final AmazonS3 amazonS3Client;
    private final String bucketName;

    @Autowired
    public AWS3Service(@Value("${amazonProperties.bucketName}") String bucketName, AmazonS3 amazonS3Client) {
        this.bucketName = bucketName;
        this.amazonS3Client = amazonS3Client;
    }

    public String uploadImage(ImageResource imageResource) {
        File file = convertMultiPartFileToFile(imageResource.getImage());

        String fileName = generateFileName(imageResource);
        String fileUrl = AMAZON_DOMAIN + bucketName + "/" + fileName;

        try {
            uploadFileToS3Bucket(file, fileName);
            file.delete();
        } catch (final AmazonServiceException ex) {
            System.out.println(ex);
        }

        return fileUrl;
    }

    public boolean deleteImageFromS3Bucket(String fileUrl) {
        String objectName = fileUrl.substring(fileUrl.lastIndexOf("/ads") + 1);

        if (amazonS3Client.doesObjectExist(bucketName, objectName)) {
            amazonS3Client.deleteObject(new DeleteObjectRequest(bucketName, objectName));

            return true;
        }

        return false;
    }

    private File convertMultiPartFileToFile(MultipartFile multipartFile) {
        final File file = new File(multipartFile.getOriginalFilename());

        try (FileOutputStream outputStream = new FileOutputStream(file);
              BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream)) {
            bufferedOutputStream.write(multipartFile.getBytes());
            bufferedOutputStream.flush();
        } catch (IOException ex) {
            System.out.println(ex);
        }

        return file;
    }

    private void uploadFileToS3Bucket(File file, String fileName) {
        amazonS3Client.putObject(
                new PutObjectRequest(bucketName, fileName, file)
        );
    }

    private String generateFileName(ImageResource imageResource) {
        long adId = imageResource.getAdId();
        MultipartFile multiPart = imageResource.getImage();

        return "ads" + "/" + adId + "/" + new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }
}
