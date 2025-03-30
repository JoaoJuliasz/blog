package com.juliasz.blog.controller;

import com.juliasz.blog.model.User;
import com.juliasz.blog.model.dto.NewPassword;
import com.juliasz.blog.model.dto.NewUserDto;
import com.juliasz.blog.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PostMapping(value = "{id}")
    public void updatePassword(@PathVariable Long id, @RequestBody NewPassword newPassword) {
        userService.updatePassword(id, newPassword);
    }

    @PatchMapping(value = "{id}")
    public User updateUser(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return userService.updateUser(updates, id);
    }

    @DeleteMapping(value = "{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
