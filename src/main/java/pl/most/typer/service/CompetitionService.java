package pl.most.typer.service;

import pl.most.typer.model.league.Competition;
import pl.most.typer.model.league.Standing;

import java.util.List;
import java.util.Optional;

public interface CompetitionService {

    Optional<Competition> findByApiId(Integer apiId);

    boolean existsCompetitionByApiId(Integer apiId);

    Competition save(Competition competition);

    List<Standing> getStandingsByCompetition(Integer competitionId);

    List<Standing> getStandingsByCompetition(Integer competitionId, String standingsType);
}
