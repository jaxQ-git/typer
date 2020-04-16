package pl.most.typer.service.footballservice;

import pl.most.typer.model.matches.Score;

import java.util.List;

public interface ScoreService {
    void save(List<Score> scoreFromMatchDTO);
}
