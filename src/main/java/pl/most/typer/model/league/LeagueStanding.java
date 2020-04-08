package pl.most.typer.model.league;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.EnableMBeanExport;

import javax.persistence.*;
import java.util.Stack;

@Data
@Entity
@RequiredArgsConstructor
public class LeagueStanding {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "standing_id")
    private Standing standing;
    private Integer position;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    private Integer playedGames;
    private Integer won;
    private Integer draw;
    private Integer lost;
    private Integer points;
    private Integer goalsFor;
    private Integer goalsAgainst;
    private Integer goalDifference;
}
