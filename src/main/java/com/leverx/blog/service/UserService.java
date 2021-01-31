package com.leverx.blog.service;

import com.leverx.blog.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto user);
    void activateUser(String token);
}
