package com.juliasz.blog.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity(name = "user_validation")
public class UserValidation {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "confirmation_token")
    private String confirmationToken;

    @Column(name = "user_id")
    private Long userId;

    public UserValidation(Long userId) {
        this.userId = userId;
        confirmationToken =  UUID.randomUUID().toString();
    }

    public UserValidation() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
