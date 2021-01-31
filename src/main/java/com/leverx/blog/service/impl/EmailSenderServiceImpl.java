package com.leverx.blog.service.impl;

import com.leverx.blog.model.ConfirmationToken;
import com.leverx.blog.model.User;
import com.leverx.blog.repository.ConfirmationTokenRepository;
import com.leverx.blog.service.EmailSenderService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public EmailSenderServiceImpl(ConfirmationTokenRepository confirmationTokenRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    public void sendConfirmationEmailToUser(User user) {
        ConfirmationToken confirmationToken = new ConfirmationToken(user);

        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("evgutich@gmail.com");
        mailMessage.setText("To confirm your account, please click here : "
                + "http://localhost:8080/users/activate?token=" + confirmationToken.getConfirmationToken());

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("evgutich@gmail.com");
        mailSender.setPassword("yizyjpvbzbhbbweq");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        mailSender.send(mailMessage);
    }

}
