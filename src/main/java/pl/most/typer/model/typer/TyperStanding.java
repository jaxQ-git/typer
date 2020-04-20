package pl.most.typer.model.typer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import pl.most.typer.model.competition.LeagueStanding;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class TyperStanding extends BaseModel {

    @ManyToOne
    private TyperCompetition typerCompetition;


}
