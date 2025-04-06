package com.juliasz.blog.repository;

import com.juliasz.blog.model.UserValidation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserValidationRepository extends JpaRepository<UserValidation, Long> {
    UserValidation findByConfirmationToken(String confirmationToken);
}
