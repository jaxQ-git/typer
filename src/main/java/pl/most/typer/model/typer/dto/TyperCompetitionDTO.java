package pl.most.typer.model.typer.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.most.typer.model.account.User;
import pl.most.typer.model.typer.TyperCompetition;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class TyperCompetitionDTO {

    @NotNull
    @NotEmpty
    private String name;

    public TyperCompetition toTyperCompetition() {
        return new TyperCompetition(this);
    }

}
