package pl.most.typer.service.accountservice;

public interface EmailService {


    void sendRegistrationMessage(
            String to, String subject, String text);
}
