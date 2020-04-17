package pl.most.typer.service.footballservice.matches;

import org.springframework.stereotype.Service;
import pl.most.typer.model.competition.Season;
import pl.most.typer.model.matches.Score;
import pl.most.typer.repository.footballrepo.ScoreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreServiceDefault implements ScoreService{

    private final ScoreRepository scoreRepository;

    public ScoreServiceDefault(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    @Override
    public void saveAll(List<Score> scoreFromMatchDTO) {
        for (Score score : scoreFromMatchDTO) {
            Optional<Score> byId = scoreRepository.findById(score.getMatch().getApiId());
            byId.orElseGet(() -> scoreRepository.save(score));
        }
    }
}
