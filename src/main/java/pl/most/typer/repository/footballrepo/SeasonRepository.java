package pl.most.typer.repository.footballrepo;

import org.springframework.data.repository.CrudRepository;
import pl.most.typer.model.competition.Season;

import java.util.Optional;

public interface SeasonRepository extends CrudRepository<Season, Long> {

    boolean existsByApiId(Integer apiId);

    Optional<Season> findByApiId(Integer apiId);
}
