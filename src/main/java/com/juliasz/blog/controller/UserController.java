package com.juliasz.blog.controller;

import com.juliasz.blog.model.User;
import com.juliasz.blog.model.dto.NewUserDto;
import com.juliasz.blog.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody NewUserDto user) {
        return userService.createUser(user);
    }
}
