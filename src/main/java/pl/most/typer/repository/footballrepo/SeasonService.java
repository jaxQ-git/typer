package pl.most.typer.repository.footballrepo;

import org.springframework.data.repository.CrudRepository;
import pl.most.typer.model.league.Season;

public interface SeasonService extends CrudRepository<Season,Long> {
}
