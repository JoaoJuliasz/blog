package com.juliasz.blog.controller;

import com.juliasz.blog.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/confirm")
    public ResponseEntity<String> confirmUserAccount(@PathVariable String token) {
        boolean isVerified = true;
        if (isVerified) {
            return ResponseEntity.ok("Account verified successfully!");
        } else {
            return ResponseEntity.badRequest().body("Invalid or expired token.");
        }
    }

}
