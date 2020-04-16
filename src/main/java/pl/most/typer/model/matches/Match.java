package pl.most.typer.model.matches;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import pl.most.typer.model.competition.Competition;
import pl.most.typer.model.competition.Season;
import pl.most.typer.model.competition.Team;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Match {

    @Id
    @JsonProperty("id")
    private Integer apiId;
    @ManyToOne
    private Competition competition;
    @ManyToOne
    private Season season;

    private String utcDate;
    private MatchStatus status;
    //    private String status;
    private Integer matchDay;
    private String stage;
    @Column(name = "groups")
    private String group;
    private String lastUpdate;

    @ManyToOne
    private Score score;
    @ManyToOne
    private Team homeTeam;
    @ManyToOne
    private Team awayTeam;

}
