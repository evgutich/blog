package com.leverx.blog.service.impl;

import com.leverx.blog.dto.UserDto;
import com.leverx.blog.exception.RoleNotFoundException;
import com.leverx.blog.exception.TokenException;
import com.leverx.blog.model.ConfirmationToken;
import com.leverx.blog.model.Role;
import com.leverx.blog.model.User;
import com.leverx.blog.model.UserRole;
import com.leverx.blog.repository.ConfirmationTokenRepository;
import com.leverx.blog.repository.RoleRepository;
import com.leverx.blog.repository.UserRepository;
import com.leverx.blog.service.EmailSenderService;
import com.leverx.blog.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;
    private final EmailSenderService emailSenderService;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    @Value("${token.expiration.hours}")
    private long expirationHours;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository, EmailSenderService emailSenderService, ConfirmationTokenRepository confirmationTokenRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
        this.emailSenderService = emailSenderService;
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    @Transactional
    @Override
    public UserDto createUser(UserDto user) {
        User createdUser = new User();
        createdUser.setFirstName(user.getFirstName());
        createdUser.setLastName(user.getLastName());
        createdUser.setEmail(user.getEmail());
        createdUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<UserRole> roles = new HashSet<>();
        roles.add(roleRepository.findByRole(Role.ROLE_USER).orElseThrow(RoleNotFoundException::new));
        createdUser.setRoles(roles);
        userRepository.save(createdUser);
        emailSenderService.sendConfirmationEmailToUser(createdUser);
        return user;
    }

    @Override
    public void activateUser(String token) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByConfirmationToken(token).orElseThrow(() -> new TokenException("Token not found"));
        long hoursAgo = ChronoUnit.HOURS.between(confirmationToken.getCreatedDate(), LocalDateTime.now());
        if (hoursAgo < expirationHours) {
            User user = confirmationToken.getUser();
            user.setActive(true);
            userRepository.save(user);
        } else {
            throw new TokenException("Token has expired");
        }
    }
}
