package pl.most.typer.service.footballservice;

import pl.most.typer.model.league.Competition;
import pl.most.typer.model.league.Season;

import java.util.List;

public interface SeasonService {

    void setCompetitionInSeasons(List<Season> seasons, Competition competition);

    Season save(Season season);

    void save(List<Season> season);
}
