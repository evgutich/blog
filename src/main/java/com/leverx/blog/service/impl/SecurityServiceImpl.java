package com.leverx.blog.service.impl;

import com.leverx.blog.model.User;
import com.leverx.blog.repository.UserRepository;
import com.leverx.blog.service.SecurityService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    private final UserRepository userRepository;

    public SecurityServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getLoggedInUser() {
        org.springframework.security.core.userdetails.User user =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(user.getUsername());
    }
}
