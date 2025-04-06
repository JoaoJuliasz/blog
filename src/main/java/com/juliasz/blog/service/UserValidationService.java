package com.juliasz.blog.service;

import com.juliasz.blog.model.UserValidation;
import com.juliasz.blog.repository.UserValidationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserValidationService {
    private final UserValidationRepository userValidationRepository;

    public UserValidationService(UserValidationRepository userValidationRepository) {
        this.userValidationRepository = userValidationRepository;
    }

    public String createOne(Long userId) {
        UserValidation userValidation = new UserValidation(userId);
        userValidationRepository.save(userValidation);
        return userValidation.getConfirmationToken();
    }

    public UserValidation findOne(String code) {
        UserValidation validation = userValidationRepository.findByConfirmationToken(code);
        if(validation == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Validation token not found!");
        }
        return validation;
    }

    public void deleteOne(String code) {
        UserValidation validation = findOne(code);
        userValidationRepository.deleteById(validation.getId());
    }
}
