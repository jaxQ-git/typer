package pl.most.typer.repository.footballrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pl.most.typer.model.league.Competition;

import java.util.Optional;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {

    Optional<Competition> findByApiId(Integer apiId);

    boolean existsCompetitionByApiId(Integer apiId);


}
