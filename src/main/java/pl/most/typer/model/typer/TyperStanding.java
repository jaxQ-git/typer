package pl.most.typer.model.typer;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class TyperStanding extends BaseModel {

    @ManyToOne
    private TyperCompetition typerCompetition;


    private Integer round=0;

    @ToString.Exclude
    @OneToMany(mappedBy = "typerStanding",cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<TyperLeagueStanding> typerLeagueStandings = new ArrayList<>();

    public TyperStanding(TyperCompetition typerCompetition) {
        this.typerCompetition=typerCompetition;
        this.round = typerCompetition.incrementedCurrentRound();
        this.typerLeagueStandings = createNewTyperLeagueStandings();
    }

    public void addTyperLeagueStanding(TyperLeagueStanding typerLeagueStanding){
        typerLeagueStandings.add(typerLeagueStanding);
        typerLeagueStanding.setTyperStanding(this);
    }

    public void removeTyperLeagueStanding(TyperLeagueStanding typerLeagueStanding) {
        typerLeagueStandings.remove(typerLeagueStanding);
        typerLeagueStanding.setTyperStanding(null);
    }

    private List<TyperLeagueStanding> createNewTyperLeagueStandings() {
        return this.typerCompetition
                .getTyperPlayers()
                .stream()
                .map(typerPlayer -> new TyperLeagueStanding(this, typerPlayer))
                .collect(Collectors.toList());
    }
}
