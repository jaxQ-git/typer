package pl.most.typer.service.footballservice.competition;

import pl.most.typer.model.competition.Competition;
import pl.most.typer.model.competition.Season;

import java.util.List;

public interface SeasonService {

    void setCompetitionInSeasons(List<Season> seasons, Competition competition);

    Season save(Season season);

    void save(List<Season> season);
}
