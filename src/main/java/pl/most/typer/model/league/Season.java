package pl.most.typer.model.league;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.most.typer.model.matches.Match;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Season {

    @Id
    @JsonProperty("id")
    private Integer apiId;

    private String startDate;
    private String endDate;

    private Integer currentMatchday;

    @ManyToOne
    private Team winner;

    //Czy nie przypadkiem ManyToMany (co jesli w bazie istnieje sezon, ale ma inna lige i chcielibysmy mu jeszcze przypisac kolejna)
    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;


    public Season() {
    }

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getCurrentMatchday() {
        return currentMatchday;
    }

    public void setCurrentMatchday(Integer currentMatchday) {
        this.currentMatchday = currentMatchday;
    }

    public Team getWinner() {
        return winner;
    }

    public void setWinner(Team winner) {
        this.winner = winner;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Season season = (Season) o;
        return Objects.equals(apiId, season.apiId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apiId);
    }
}
