package pl.most.typer.service.footballservice.competition;

import pl.most.typer.model.competition.Competition;

import java.util.List;
import java.util.Optional;

public interface CompetitionService {

    Competition findByApiId(Integer apiId);

    boolean existsByApiId(Integer apiId);

    Competition save(Competition competition);

    Competition update(Competition competition);

    Competition getCompetition(Integer id);

    String getCompetitionName(Integer id);

    List<Competition> getAll();

    boolean isCompetitionInDBUpToDate(Competition competition);
}
