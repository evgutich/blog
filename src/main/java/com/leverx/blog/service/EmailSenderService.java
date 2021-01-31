package com.leverx.blog.service;

import com.leverx.blog.model.User;
import org.springframework.mail.SimpleMailMessage;

public interface EmailSenderService {
    public void sendConfirmationEmailToUser(User user);
}
