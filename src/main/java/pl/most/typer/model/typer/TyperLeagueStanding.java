package pl.most.typer.model.typer;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
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

    @ColumnDefault("1")
    private Integer position;

    @ManyToOne
    private TyperPlayer typerPlayer;
    @ColumnDefault("0")
    private Integer points;

}
