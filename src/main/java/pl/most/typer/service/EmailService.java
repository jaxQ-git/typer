package pl.most.typer.service;

public interface EmailService {


    void sendRegistrationMessage(
            String to, String subject, String text);
}
