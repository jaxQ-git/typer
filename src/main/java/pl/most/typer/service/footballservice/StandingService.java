package pl.most.typer.service.footballservice;

import pl.most.typer.model.league.Competition;
import pl.most.typer.model.league.Standing;

import java.util.List;

public interface StandingService {

    Standing save(Standing standing);

    void saveAll(List<Standing> standings);
    List<Standing> getStandingsByCompetition(Competition competition);
    List<Standing> getStandingsByCompetition(Competition competition, String standingsType);

    void setStandingInLeagueStanding(List<Standing> standings);

    void setCompetitionInStandings(List<Standing> standings, Competition competition);
}
