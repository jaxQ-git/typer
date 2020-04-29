package pl.most.typer.repository.footballrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.most.typer.model.competition.Competition;
import pl.most.typer.model.competition.Standing;

import java.util.List;
import java.util.Optional;

public interface StandingRepository extends JpaRepository<Standing, Long> {

    List<Standing> findAllByCompetitionAndType(Competition competition, String type);

    List<Standing> findAllByCompetition(Competition competition);


}
