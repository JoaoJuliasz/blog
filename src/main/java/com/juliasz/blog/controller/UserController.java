package com.juliasz.blog.controller;

import com.juliasz.blog.model.User;
import com.juliasz.blog.model.dto.NewUserDto;
import com.juliasz.blog.service.UserService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "{id}")
    public User findOne(@PathVariable Long id) {
        return userService.findOne(id);
    }

    @PostMapping
    public User createUser(@RequestBody NewUserDto user) {
        return userService.createUser(user);
    }

    @DeleteMapping(value = "{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
