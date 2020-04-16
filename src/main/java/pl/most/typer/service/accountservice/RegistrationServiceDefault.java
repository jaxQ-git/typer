package pl.most.typer.service.accountservice;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.most.typer.model.account.RoleType;
import pl.most.typer.repository.accountrepo.RoleRepository;
import pl.most.typer.repository.accountrepo.UserRepository;
import pl.most.typer.model.account.User;
import pl.most.typer.model.account.RegistrationForm;

import java.util.Optional;

@Service
public class RegistrationServiceDefault implements RegistrationService {



    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private EmailService emailService;
    private RoleRepository roleRepository;

    public RegistrationServiceDefault(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.roleRepository = roleRepository;
    }



//TODO Moze niezauwazylem, ale nigdzie nie widzialem obslugiwania roli dla uzytkownikowi przy rejestracji,
// ewentualnie mozna to tak zostawic, a pozniej stworzyc jakis panel admina, ktory bedzie mial dostep do bazy uzytkownikow
// i tam moglby danemu nadac role admina, a kazdy inny defaultowo ma user


    @Override
    public boolean register(RegistrationForm registrationForm) {
        if (userRepository.findByUsernameOrMail(registrationForm.getUsername(),registrationForm.getMail()).isPresent()) {
            return false;
        }
        User user = registrationForm.toUser(passwordEncoder);
        User savedUser = userRepository.save(user);
        sendConfirmationMailMessage(savedUser);
        return true;

    }

    @Override
    public void confirmUser(String key) {
        Optional<User> userOptional = userRepository.findByToken(key);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setEnabled(true);
            user.setToken(null);
            user.getRoles().add(roleRepository.findByRoleType(RoleType.USER));
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
