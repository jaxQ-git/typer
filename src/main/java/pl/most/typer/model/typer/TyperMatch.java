package pl.most.typer.model.typer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.most.typer.model.matches.Match;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)

public class TyperMatch extends BaseModel {

    @ManyToOne
    private Match match;

}
