package pl.most.typer.model.matches;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String winner;
    private String duration;

    @OneToOne
    private TeamGoals fullTime;
    @OneToOne
    private TeamGoals halfTime;
    @OneToOne
    private TeamGoals extraTime;
    @OneToOne
    private TeamGoals penalties;

}
