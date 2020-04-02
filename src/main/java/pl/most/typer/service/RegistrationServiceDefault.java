package pl.most.typer.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.most.typer.data.UserRepository;
import pl.most.typer.security.RegistrationForm;
import pl.most.typer.security.User;

import java.util.Optional;
import java.util.UUID;

@Service
public class RegistrationServiceDefault implements RegistrationService {



    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private EmailService emailService;

    public RegistrationServiceDefault(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }




    @Override
    public void register(RegistrationForm registrationForm) {
        User user = registrationForm.toUser(passwordEncoder);
        User savedUser = userRepository.save(user);
        sendConfirmationMailMessage(savedUser);

    }

    @Override
    public void confirmUser(String key) {
        Optional<User> userOptional = userRepository.findByToken(key);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setEnabled(true);
            user.setToken(null);
            userRepository.save(user);
        }
    }

    private void sendConfirmationMailMessage(User savedUser) {
        String link = "http://localhost:8080/register/confirm?key=" + savedUser.getToken();
        emailService
                .sendRegistrationMessage(
                        savedUser.getMail(),
                        MAIL_SUBJECT,
                        MAIL_MESSAGE + "\n " + link);
    }

}
