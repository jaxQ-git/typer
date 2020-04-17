package pl.most.typer.service.footballservice.matches;

import org.springframework.http.HttpStatus;
import pl.most.typer.model.matches.Match;

import java.util.List;

public interface MatchesService {

    HttpStatus getMatchesByCompetitionId(Integer competitionId);

    void saveAll(List<Match> matches);
}
