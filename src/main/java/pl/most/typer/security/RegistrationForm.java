package pl.most.typer.security;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {

    private final String username;
    private final String password;
    private final String mail;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username,
                passwordEncoder.encode(password),
                mail);
    }
}
