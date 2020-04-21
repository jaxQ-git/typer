package pl.most.typer.model.typer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import pl.most.typer.model.competition.LeagueStanding;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class TyperStanding extends BaseModel {

    @ManyToOne
    private TyperCompetition typerCompetition;

    @ColumnDefault("0")
    private Integer round;

    @OneToMany(mappedBy = "typerStanding")
    private List<TyperLeagueStanding> typerLeagueStandings;
}
