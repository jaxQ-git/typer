package pl.most.typer.repository.footballrepo;

import org.springframework.data.repository.CrudRepository;
import pl.most.typer.model.league.Standing;

public interface StandingRepository extends JpaRepository<Standing,Long> {

    List<Standing> findAllByCompetitionAndType(Competition competition, String type);
    List<Standing> findAllByCompetition(Competition competition);
}
