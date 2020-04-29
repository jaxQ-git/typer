package pl.most.typer.service.footballservice.competition;

import pl.most.typer.exceptions.ResourceAlreadyExistsException;
import pl.most.typer.exceptions.ResourceNotFoundException;
import pl.most.typer.model.competition.Competition;
import pl.most.typer.model.competition.Standing;

import java.util.List;
import java.util.Optional;

public interface StandingService {

    Standing save(Standing standing) throws ResourceAlreadyExistsException;

    void saveOrUpdateAll(List<Standing> standings);

    List<Standing> getStandingsByCompetition(Competition competition);

    List<Standing> getStandingsByCompetition(Competition competition, String standingsType);

    void setStandingInLeagueStanding(List<Standing> standings);

    void setCompetitionInStandings(List<Standing> standings, Competition competition);

    boolean existsById(Long id);
}
