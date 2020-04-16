package pl.most.typer.model.matches;

import lombok.Data;
import pl.most.typer.model.competition.Competition;

import javax.persistence.*;


@Data
@Entity
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String winner;
    private String duration;

    @OneToOne(cascade = CascadeType.ALL)
    private TeamGoals fullTime;
    @OneToOne(cascade = CascadeType.ALL)
    private TeamGoals halfTime;
    @OneToOne(cascade = CascadeType.ALL)
    private TeamGoals extraTime;
    @OneToOne(cascade = CascadeType.ALL)
    private TeamGoals penalties;

    @OneToOne(cascade = CascadeType.ALL)
    private Match match;
}
