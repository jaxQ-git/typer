package pl.most.typer.service.footballservice;

import pl.most.typer.model.league.Competition;
import pl.most.typer.model.league.Standing;

import java.util.List;
import java.util.Optional;

public interface CompetitionService {

    Optional<Competition> findByApiId(Integer apiId);

    boolean existsCompetitionByApiId(Integer apiId);

    Competition save(Competition competition);

    Optional<Competition> getCompetition(Integer id);

    String getCompetitionName(Integer id);

    List<Competition> getAll();
}
