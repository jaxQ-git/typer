package pl.most.typer.repository;

import org.springframework.data.repository.CrudRepository;
import pl.most.typer.model.league.Season;

public interface SeasonRepository extends CrudRepository<Season,Long> {
}
