package pl.most.typer.model.matches;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import pl.most.typer.model.competition.Competition;
import pl.most.typer.model.competition.Season;
import pl.most.typer.model.competition.Team;

import javax.persistence.*;
import java.util.Objects;


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

    @Enumerated(EnumType.STRING)
    private MatchStatus status;
    private Integer matchDay;
    private String stage;
    @Column(name = "groups")
    private String group;
    private String lastUpdate;

    @OneToOne(cascade = CascadeType.ALL)
    private Score score;
    @OneToOne
    private Team homeTeam;
    @OneToOne
    private Team awayTeam;

    public Match() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return Objects.equals(apiId, match.apiId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apiId);
    }

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public String getUtcDate() {
        return utcDate;
    }

    public void setUtcDate(String utcDate) {
        this.utcDate = utcDate;
    }

    public MatchStatus getStatus() {
        return status;
    }

    public void setStatus(MatchStatus status) {
        this.status = status;
    }

    public Integer getMatchDay() {
        return matchDay;
    }

    public void setMatchDay(Integer matchDay) {
        this.matchDay = matchDay;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }
}
