package com.juliasz.blog.controller;

import com.juliasz.blog.model.dto.FileUrlDto;
import com.juliasz.blog.service.AmazonClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/bucket")
public class BucketController {
    private final AmazonClientService amazonClient;

    @Autowired
    BucketController(AmazonClientService amazonClient) {
        this.amazonClient = amazonClient;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestPart(value = "file") MultipartFile file) {
        String filePath = this.amazonClient.uploadFile(file);
        return ResponseEntity.ok(filePath);
    }

    @DeleteMapping("/delete")
    public void deleteFile(@RequestBody FileUrlDto fileUrlDto) {
         this.amazonClient.deleteFile(fileUrlDto.getFileUrl());
    }

}
