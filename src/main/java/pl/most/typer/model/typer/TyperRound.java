package pl.most.typer.model.typer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class TyperRound extends BaseModel {

    @ToString.Exclude
    @ManyToOne
    private TyperCompetition typerCompetition;


    private Integer number=0;
}
