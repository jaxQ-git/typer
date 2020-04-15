package pl.most.typer.model.league;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.context.annotation.EnableMBeanExport;

import javax.persistence.*;
import java.util.Comparator;
import java.util.Objects;

@Data
@Entity
public class LeagueStanding {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "standing_id")
    private Standing standing;
    private Integer position;
    @ManyToOne()
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



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeagueStanding that = (LeagueStanding) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
