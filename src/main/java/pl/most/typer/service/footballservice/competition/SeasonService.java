package pl.most.typer.service.footballservice.competition;

import pl.most.typer.exceptions.BadResourceException;
import pl.most.typer.exceptions.ResourceNotFoundException;
import pl.most.typer.model.competition.Competition;
import pl.most.typer.model.competition.Season;

import java.util.List;

public interface SeasonService {

    void setCompetitionInSeasons(List<Season> seasons, Competition competition);

    Season saveOrUpdate(Season season);

    void saveOrUpdateAll(List<Season> season);

    boolean existsByApiId(Integer apiId);
}

