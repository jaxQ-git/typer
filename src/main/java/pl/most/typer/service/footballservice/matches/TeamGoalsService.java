package pl.most.typer.service.footballservice.matches;

import pl.most.typer.model.matches.TeamGoals;

import java.util.List;

public interface TeamGoalsService {
    void save(List<TeamGoals> scoreFromMatchDTO);
}
