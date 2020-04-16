package pl.most.typer.service.footballservice;

import org.springframework.stereotype.Service;
import pl.most.typer.model.matches.Match;
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

    //TODO Pomyslec nad zapisywaniem tego do bazy (przynajmniej zeby w locie przypisywac ID meczu,
    // zeby drugi raz nie zapisywal)
    @Override
    public void save(List<Score> scoreFromMatchDTO) {
        for (Score scoreList : scoreFromMatchDTO) {
            scoreRepository.save(scoreList);
        }
    }
}
