package pl.most.typer.service.footballservice;

import pl.most.typer.model.matches.Score;
import pl.most.typer.model.matches.TeamGoals;

import java.util.List;

public interface TeamGoalsService {
    void save(List<TeamGoals> scoreFromMatchDTO);
}
