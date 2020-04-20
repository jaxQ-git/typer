package pl.most.typer.model.typer;

import lombok.Data;
import pl.most.typer.model.account.User;

import javax.persistence.*;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
public class TyperPlayer extends BaseModel {

    @OneToOne
    private User user;

    private String name = "default";

    private String surname = "default";

    @ManyToMany(mappedBy = "typerPlayers")
    Collection<TyperCompetition> typerCompetitions = new ArrayList<>();


}
