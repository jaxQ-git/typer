package pl.most.typer.service.footballservice.competition;

import pl.most.typer.model.competition.Competition;
import pl.most.typer.model.competition.Standing;

import java.util.List;

public interface StandingService {

    Standing save(Standing standing);

    void saveAll(List<Standing> standings);
    List<Standing> getStandingsByCompetition(Competition competition);
    List<Standing> getStandingsByCompetition(Competition competition, String standingsType);

    void setStandingInLeagueStanding(List<Standing> standings);

    void setCompetitionInStandings(List<Standing> standings, Competition competition);
}
