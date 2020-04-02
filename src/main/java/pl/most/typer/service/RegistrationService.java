package pl.most.typer.service;

import pl.most.typer.security.RegistrationForm;

import java.util.UUID;

public interface RegistrationService {

    String MAIL_MESSAGE = "Dziękujemy, że dołączyłeś do nas.\n" +
            "Jeszcze tylko jeden krok...\n" +
            "Aby aktywować konto, kliknij poniższy link:";
    String MAIL_SUBJECT = "Aktywuj swoje konto";
    void register(RegistrationForm registrationForm);

    void confirmUser(String key);
}
