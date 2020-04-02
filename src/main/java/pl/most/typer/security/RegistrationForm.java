package pl.most.typer.security;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@Data
public class RegistrationForm {

    private final String username;
    private final String password;
    private final String mail;

    public User toUser(PasswordEncoder passwordEncoder) {
        User newUser = new User(
                username,
                passwordEncoder.encode(password),
                mail);
        newUser.setToken(UUID.randomUUID().toString());
        return newUser;
    }
}
