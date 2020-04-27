package pl.most.typer.model.typer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import pl.most.typer.model.account.User;

import javax.persistence.*;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)

public class TyperPlayer extends BaseModel {

    @OneToOne
    private User user;


    private String name="";


    private String surname="";

    @ToString.Exclude
    @ManyToMany(mappedBy = "typerPlayers")
    Collection<TyperCompetition> typerCompetitions = new ArrayList<>();


    public void addTyperCompetition(TyperCompetition typerCompetition) {
        if (!isAssignedToTyperCompetition(typerCompetition)) {
            this.typerCompetitions.add(typerCompetition);
            if (!typerCompetition.getTyperPlayers().contains(this)) {
                typerCompetition.addTyperPlayer(this);
            }
        }

    }

    public void removeTyperCompetition(TyperCompetition typerCompetition) {
        this.typerCompetitions.remove(typerCompetition);
        if (typerCompetition.isParticipating(this)) {
            typerCompetition.removeTyperPlayer(this);
        }

    }

    public boolean isAssignedToTyperCompetition(TyperCompetition typerCompetition) {
        return this.typerCompetitions.contains(typerCompetition);
    }
}
