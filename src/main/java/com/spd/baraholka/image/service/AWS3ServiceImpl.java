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

    private static final String ILLEGAL_FILE_URL = "Illegal file URL. Provided file URL does not belong to SPD-Baraholka S3 bucket.";

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
        String objectName = getObjectNameFromUrl(fileUrl);

        if (amazonS3Client.doesObjectExist(bucketName, objectName)) {
            amazonS3Client.deleteObject(new DeleteObjectRequest(bucketName, objectName));

            return true;
        }

        return false;
    }

    private String getObjectNameFromUrl(String fileUrl) {
        Objects.requireNonNull(fileUrl);
        String bucketUrl = amazonDomain + bucketName + "/";
        String[] fileUrlParts = fileUrl.split(bucketUrl);
        if (fileUrlParts.length < 2) {
            throw new IllegalArgumentException(ILLEGAL_FILE_URL);
        }
        return fileUrlParts[fileUrlParts.length - 1];
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
