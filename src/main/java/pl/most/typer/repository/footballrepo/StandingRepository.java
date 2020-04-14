package pl.most.typer.repository.footballrepo;

import org.springframework.data.repository.CrudRepository;
import pl.most.typer.model.league.Competition;
import pl.most.typer.model.league.Standing;

import java.util.List;

public interface StandingRepository extends CrudRepository<Standing,Long> {

    List<Standing> findAllByCompetitionAndType(Competition competition,String type);
}
