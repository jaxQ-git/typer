package pl.most.typer.model.typer;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
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


    private String name="";


    private String surname="";

    @ToString.Exclude
    @ManyToMany(mappedBy = "typerPlayers")
    Collection<TyperCompetition> typerCompetitions = new ArrayList<>();


}
