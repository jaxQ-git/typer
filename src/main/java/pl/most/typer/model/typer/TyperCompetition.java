package pl.most.typer.model.typer;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.UniqueElements;
import pl.most.typer.model.typer.dto.TyperCompetitionDTO;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class TyperCompetition extends BaseModel {



    @ToString.Exclude
    @ManyToMany
    private Collection<TyperPlayer> typerPlayers;

    @Column(unique = true)
    private String name;



    private Integer currentRound;

    private LocalDateTime lastUpdated;

    public TyperCompetition(String name) {
        this.lastUpdated = LocalDateTime.now();
        this.typerPlayers = new ArrayList<>();
        this.name = name;
        this.currentRound=0;
    }

    public TyperCompetition(TyperCompetitionDTO typerCompetitionDTO) {
        this.name = typerCompetitionDTO.getName();
        this.lastUpdated = LocalDateTime.now();
        this.typerPlayers = new ArrayList<>();
        this.name = name;
        this.currentRound=0;
    }
}
