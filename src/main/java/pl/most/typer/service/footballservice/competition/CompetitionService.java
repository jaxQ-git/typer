package pl.most.typer.service.footballservice.competition;

import pl.most.typer.model.competition.Competition;

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
