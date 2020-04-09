package pl.most.typer.repository;

import org.springframework.data.repository.CrudRepository;
import pl.most.typer.model.league.Team;

public interface TeamRepository extends CrudRepository<Team,Long> {
}
