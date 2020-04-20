package pl.most.typer.model.typer;

import lombok.Data;
import pl.most.typer.model.competition.Standing;
import pl.most.typer.model.competition.Team;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
public class TyperLeagueStanding extends BaseModel {

    @ManyToOne
    private TyperStanding typerStanding;


    private Integer position;

    @ManyToOne
    private TyperPlayer typerPlayer;

    private Integer points;

    //TODO Usunąć po wprowadzeniu logiki do liczenia pozycji
    public TyperLeagueStanding() {
        this.points=0;
        this.position=1;
    }
}
