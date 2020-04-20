package pl.most.typer.model.matches;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;


@Data
@Entity
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private ScoreWinner winner;

    @Enumerated(EnumType.STRING)
    private ScoreDuration duration;

    @OneToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    private TeamGoals fullTime;
    @OneToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    private TeamGoals halfTime;
    @OneToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    private TeamGoals extraTime;
    @OneToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    private TeamGoals penalties;

    @OneToOne
    private Match match;


}
