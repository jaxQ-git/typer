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
public class LeagueTyperStanding extends BaseModel {

    @ManyToOne
    private TyperStanding typerStanding;

    @ManyToOne
    private TyperPlayer typerPlayer;

    private Integer round;

    private Integer points;




}
