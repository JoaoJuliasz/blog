package com.juliasz.blog.service;

import com.juliasz.blog.model.User;
import com.juliasz.blog.model.dto.NewPassword;
import com.juliasz.blog.model.dto.NewUserDto;
import com.juliasz.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordService passwordService;

    @Autowired
    public UserService(UserRepository userRepository, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOne(Long id) {
        Optional<User> foundUser = userRepository.findById(id);
        if(foundUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return foundUser.get();
    }

    public User createUser(NewUserDto newUser) {
        if (validateUserEmail(newUser.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This email already exists");
        }
        User user = new User(newUser);
        user.setPassword(passwordService.encryptPassword(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    public void deleteUser(Long id) {
        findOne(id);
        userRepository.deleteById(id);
    }

    public void updatePassword(Long id, NewPassword newPassword) {
        User user = findOne(id);
        passwordService.validatePassword(user.getPassword(), newPassword.getOldPassword());
        user.setPassword(passwordService.encryptPassword(newPassword.getNewPassword()));
        userRepository.save(user);
    }

    private boolean validateUserEmail(String email) {
        return userRepository.existsByEmail(email);
    }

}
