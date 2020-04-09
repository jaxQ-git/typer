package pl.most.typer.repository;

import org.springframework.data.repository.CrudRepository;
import pl.most.typer.model.league.Team;

import java.util.Optional;

public interface TeamRepository extends CrudRepository<Team,Long> {

    boolean existsByApiId(Integer apiId);

    Optional<Team> findByApiId(Integer apiId);
}
