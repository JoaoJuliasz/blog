package com.juliasz.blog.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PasswordService {
    private final BCryptPasswordEncoder passwordEncoder;

    public PasswordService(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public void validatePassword(String userPassword, String oldPassword) {
        if (!passwordEncoder.matches(oldPassword, userPassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords does not match");
        }
    }
}
