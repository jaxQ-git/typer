package pl.most.typer.service.footballservice;

import org.springframework.stereotype.Service;
import pl.most.typer.model.league.Season;
import pl.most.typer.repository.footballrepo.SeasonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SeasonServiceDefault implements SeasonService {

    private SeasonRepository seasonRepository;

    public SeasonServiceDefault(SeasonRepository seasonRepository) {
        this.seasonRepository = seasonRepository;
    }

    @Override
    public void save(List<Season> season) {
        for (Season seasonList : season) {
            Optional<Season> byApiId = seasonRepository.findByApiId(seasonList.getApiId());
            byApiId.orElseGet(() -> seasonRepository.save(seasonList));
        }
    }

}
