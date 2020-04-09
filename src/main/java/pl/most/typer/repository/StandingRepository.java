package pl.most.typer.repository;

import org.springframework.data.repository.CrudRepository;
import pl.most.typer.model.league.Standing;

public interface StandingRepository extends CrudRepository<Standing,Long> {
}
