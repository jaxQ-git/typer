package pl.most.typer.repository.footballrepo;

import org.springframework.data.repository.CrudRepository;
import pl.most.typer.model.matches.Match;

import java.util.Optional;

public interface MatchesRepository extends CrudRepository<Match, Integer> {

    Optional<Match> findByApiId(Integer id);
}
