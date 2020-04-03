package pl.most.typer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceDefault implements EmailService {

    public final JavaMailSender emailSender;

    public EmailServiceDefault(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendRegistrationMessage(
            String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
