package pl.most.typer.service.footballservice;

import org.springframework.stereotype.Service;
import pl.most.typer.model.league.Competition;
import pl.most.typer.model.league.Season;
import pl.most.typer.repository.footballrepo.SeasonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SeasonServiceDefault implements SeasonService {

    SeasonRepository seasonRepository;

    public SeasonServiceDefault(SeasonRepository seasonRepository) {
        this.seasonRepository = seasonRepository;
    }

    @Override
    public void setCompetitionInSeasons(List<Season> seasons, Competition competition) {
        seasons.forEach(season -> season.setCompetition(competition));
    }

    @Override
    public Season save(Season season) {
        Optional<Season> optionalSeason = seasonRepository.findByApiId(season.getApiId());
        if (optionalSeason.isPresent()) {
            Season seasonDB = optionalSeason.get();
            if(!seasonDB.getCurrentMatchday().equals(season.getCurrentMatchday())){
                seasonDB.setCurrentMatchday(season.getCurrentMatchday());
                seasonDB.setCompetition(seasonDB.getCompetition());
                seasonDB.setWinner(season.getWinner());
                seasonDB.setStartDate(season.getStartDate());
                seasonDB.setEndDate(season.getEndDate());
                seasonDB = seasonRepository.save(seasonDB);
            }
            return seasonDB;
        }
        else {
            return seasonRepository.save(season);
        }
    }
}
