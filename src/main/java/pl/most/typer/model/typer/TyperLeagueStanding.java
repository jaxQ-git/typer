package pl.most.typer.model.typer;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import pl.most.typer.model.competition.Standing;
import pl.most.typer.model.competition.Team;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TyperLeagueStanding extends BaseModel {

    @ToString.Exclude
    @ManyToOne
    private TyperStanding typerStanding;


    private Integer position=1;

    @ManyToOne
    private TyperPlayer typerPlayer;

    private Integer points=0;

    public TyperLeagueStanding(TyperStanding typerStanding, TyperPlayer typerPlayer) {
        this.typerPlayer = typerPlayer;
        this.typerStanding = typerStanding;
    }
}
