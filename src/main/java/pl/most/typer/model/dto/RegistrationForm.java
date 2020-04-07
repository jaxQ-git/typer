package pl.most.typer.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.most.typer.model.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true)
public class RegistrationForm {

    @NotEmpty(message = "Username cannot be empty")
    private String username;
    @Size(min = 4, message = "Password should be at least 4 characters")
    private String password;
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Please enter a valid email")
    private String mail;

    public RegistrationForm(String username, String password, String mail) {
        this.username = username;
        this.password = password;
        this.mail = mail;
    }

    public User toUser(PasswordEncoder passwordEncoder) {
        User newUser = User.createUser(
                username,
                passwordEncoder.encode(password),
                mail);
        newUser.setToken(UUID.randomUUID().toString());
        return newUser;
    }
}
