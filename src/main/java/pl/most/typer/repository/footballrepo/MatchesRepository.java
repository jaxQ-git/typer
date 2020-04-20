package pl.most.typer.repository.footballrepo;

import org.springframework.data.repository.CrudRepository;
import pl.most.typer.model.competition.Competition;
import pl.most.typer.model.matches.Match;
import pl.most.typer.service.footballservice.competition.CompetitionService;

import java.util.List;
import java.util.Optional;

public interface MatchesRepository extends CrudRepository<Match, Integer> {

    Optional<Match> findByApiId(Integer id);

    List<Match> findAllByCompetition(Competition competitionId);
}
