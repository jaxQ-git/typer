package pl.most.typer.model.typer;

import lombok.Data;
import pl.most.typer.model.matches.Match;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@Entity
public class TyperMatch extends BaseModel {

    @ManyToOne
    private Match match;

}
