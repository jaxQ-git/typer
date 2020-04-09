package pl.most.typer.service;

import org.springframework.data.repository.CrudRepository;
import pl.most.typer.model.league.Competition;

import java.util.Optional;

public interface CompetitionService {

    Optional<Competition> findByApiId(Integer apiId);

    boolean existsCompetitionByApiId(Integer apiId);

    Competition save(Competition competition);
}
