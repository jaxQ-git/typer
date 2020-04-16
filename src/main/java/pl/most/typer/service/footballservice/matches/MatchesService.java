package pl.most.typer.service.footballservice.matches;

import pl.most.typer.model.matches.Match;

import java.util.List;

public interface MatchesService {

    void getMatchesByCompetitionId(Integer competitionId);

    void save(List<Match> matches);
}
