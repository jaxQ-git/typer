package pl.most.typer.repository;

import org.springframework.data.repository.CrudRepository;
import pl.most.typer.model.league.Competition;

import java.util.Optional;

public interface CompetitionRepository extends CrudRepository<Competition, Long> {

    Optional<Competition> findByApiId(Integer apiId);

    boolean existsCompetitionByApiId(Integer apiId);
}
