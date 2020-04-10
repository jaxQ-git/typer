package pl.most.typer.service.accountservice;

import pl.most.typer.model.dto.RegistrationForm;

public interface RegistrationService {

    String MAIL_MESSAGE = "Dziękujemy, że dołączyłeś do nas.\n" +
            "Jeszcze tylko jeden krok...\n" +
            "Aby aktywować konto, kliknij poniższy link:";
    String MAIL_SUBJECT = "Aktywuj swoje konto";
    boolean register(RegistrationForm registrationForm);

    void confirmUser(String key);
}
