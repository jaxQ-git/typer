package pl.most.typer.model.dto;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.most.typer.model.User;
import pl.most.typer.model.role.Role;
import pl.most.typer.model.role.RoleType;

import java.util.UUID;

@Data
public class RegistrationForm {

    private final String username;
    private final String password;
    private final String mail;

    public User toUser(PasswordEncoder passwordEncoder) {
        User newUser = User.createUser(
                username,
                passwordEncoder.encode(password),
                mail);
        newUser.setToken(UUID.randomUUID().toString());
        return newUser;
    }
}
