package pl.most.typer.model.typer;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Data
@Entity
public class TyperRound extends BaseModel {

    @ManyToOne
    private TyperCompetition typerCompetition;

    private Integer number;
}
