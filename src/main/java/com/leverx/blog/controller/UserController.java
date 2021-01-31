package com.leverx.blog.controller;

import com.leverx.blog.dto.UserDto;
import com.leverx.blog.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void createUser(@RequestBody UserDto user) {
        userService.createUser(user);
    }

    @GetMapping("/activate")
    public void activateUser(@RequestParam String token) {
        userService.activateUser(token);
    }
}
