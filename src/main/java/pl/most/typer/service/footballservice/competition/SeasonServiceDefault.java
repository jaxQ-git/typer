package pl.most.typer.service.footballservice.competition;

import org.springframework.stereotype.Service;
import pl.most.typer.exceptions.ResourceAlreadyExistsException;
import pl.most.typer.exceptions.ResourceNotFoundException;
import pl.most.typer.model.competition.Competition;
import pl.most.typer.model.competition.Season;
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
    public boolean existsByApiId(Integer apiId) {
        return seasonRepository.existsByApiId(apiId);
    }

    private Season findByApiId(Integer apiId) {
        Optional<Season> seasonOptional = seasonRepository.findByApiId(apiId);
        return seasonOptional.orElseThrow(() -> {
            ResourceNotFoundException ex = new ResourceNotFoundException("Cannot find Season with id: " + apiId);
            ex.setResource("season");
            return ex;
        });
    }

    private Season save(Season season) {
        if (!existsByApiId(season.getApiId())) {
            return seasonRepository.save(season);
        }
        else {
            ResourceAlreadyExistsException ex = new ResourceAlreadyExistsException("Season with id: " + season.getApiId()
            );
            ex.setResource("Season");
            ex.setIssue("id");
            throw ex;
        }
    }

    @Override
    public Season saveOrUpdate(Season season) {
        try {
            return save(season);
        } catch (ResourceAlreadyExistsException ex) {
            return update(season);
        }
    }

    public void saveOrUpdateAll(List<Season> seasons) {
        for (Season season : seasons) {
            saveOrUpdate(season);
        }
    }


    public Season update(Season season) throws ResourceNotFoundException {
        Season seasonDB = findByApiId(season.getApiId());
        seasonDB.setCurrentMatchday(season.getCurrentMatchday());
        seasonDB.setCompetition(seasonDB.getCompetition());
        seasonDB.setWinner(season.getWinner());
        seasonDB.setStartDate(season.getStartDate());
        seasonDB.setEndDate(season.getEndDate());
        seasonDB = seasonRepository.save(seasonDB);
        return seasonRepository.save(seasonDB);
    }

    @Override
    public void setCompetitionInSeasons(List<Season> seasons, Competition competition) {
        seasons.forEach(season -> season.setCompetition(competition));
    }
}
