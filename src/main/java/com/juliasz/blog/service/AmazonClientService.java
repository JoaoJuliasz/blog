package com.juliasz.blog.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

@Service
public class AmazonClientService {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    public String uploadFile(MultipartFile multipartFile) {
        String fileName = generateFileName(multipartFile.getOriginalFilename());
        try {
            File convertedFile = convertMultiPartiToFile(multipartFile);
            amazonS3.putObject(new PutObjectRequest(bucketName, fileName, convertedFile));
            convertedFile.delete();
        } catch(Exception e) {
            throw new RuntimeException("Error uploading file to S3", e);
        }
        return amazonS3.getUrl(bucketName, fileName).toString();
    }

    private File convertMultiPartiToFile(MultipartFile file) throws IOException{
        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        }
        return convFile;
    }

    private String generateFileName(String originalFilename) {
        return new Date().getTime() + "-" + originalFilename.replace(" ", "_");
    }

    public void deleteFile(String fileUrl) {
        try {
            String fileName = extractKeyFromUrl(fileUrl);
            DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucketName, fileName);
            amazonS3.deleteObject(deleteObjectRequest);
        } catch(Exception e) {
            throw new RuntimeException("Error deleting file from S3", e);
        }
    }

    private String extractKeyFromUrl(String fileUrl) {
        try {
            URL url = new URL(fileUrl);
            return url.getPath().substring(1);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid file URL", e);
        }
    }

}