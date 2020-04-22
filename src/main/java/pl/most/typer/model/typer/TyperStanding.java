package pl.most.typer.model.typer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import pl.most.typer.model.competition.LeagueStanding;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)

public class TyperStanding extends BaseModel {

    @ManyToOne
    private TyperCompetition typerCompetition;


    private Integer round=0;

    @ToString.Exclude
    @OneToMany(mappedBy = "typerStanding",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TyperLeagueStanding> typerLeagueStandings = new ArrayList<>();
}
