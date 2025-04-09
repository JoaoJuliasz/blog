package com.juliasz.blog.model.dto;

public class AuthRequest {
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    private String email;
    private String password;

}
