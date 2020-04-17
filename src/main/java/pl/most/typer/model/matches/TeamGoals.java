package pl.most.typer.model.matches;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import pl.most.typer.model.competition.Competition;

import javax.persistence.*;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamGoals {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer homeTeam;
    private Integer awayTeam;

    @OneToOne
    private Score score;

}