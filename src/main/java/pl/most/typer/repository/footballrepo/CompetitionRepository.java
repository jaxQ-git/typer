package pl.most.typer.repository.footballrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.most.typer.model.competition.Competition;

import java.util.Optional;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {

    Optional<Competition> findByApiId(Integer apiId);

    boolean existsByApiId(Integer apiId);


}
