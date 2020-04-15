package pl.most.typer.repository.footballrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pl.most.typer.model.league.Competition;
import pl.most.typer.model.league.Season;

import java.util.List;
import java.util.Optional;

public interface SeasonRepository extends JpaRepository<Season,Long> {

    Optional<Season> findByApiId(Integer apiId);

}
