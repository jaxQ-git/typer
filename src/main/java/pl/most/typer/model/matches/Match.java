package pl.most.typer.model.matches;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import pl.most.typer.model.competition.Competition;
import pl.most.typer.model.competition.Season;
import pl.most.typer.model.competition.Team;

import javax.persistence.*;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Match {

    @Id
    @JsonProperty("id")
    private Integer apiId;
    @ManyToOne(cascade = CascadeType.ALL)
    private Competition competition;
    @ManyToOne
    private Season season;

    private String utcDate;
    private MatchStatus status;
    private Integer matchDay;
    private String stage;
    @Column(name = "groups")
    private String group;
    private String lastUpdate;

    @OneToOne
    private Score score;
    @OneToOne
    private Team homeTeam;
    @OneToOne
    private Team awayTeam;

}
