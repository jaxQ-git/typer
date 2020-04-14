package pl.most.typer.service.footballservice;

import org.springframework.stereotype.Service;
import pl.most.typer.model.league.Competition;
import pl.most.typer.model.league.Standing;
import pl.most.typer.repository.footballrepo.StandingRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
///
public class StandingServiceDefault implements StandingService {

    private final String defaultStandingsType = "TOTAL";
    private StandingRepository standingRepository;

    public StandingServiceDefault(StandingRepository standingRepository) {
        this.standingRepository = standingRepository;
    }

    @Override
    public void saveAll(List<Standing> standings) {
        standingRepository.saveAll(standings);
    }


    /**
     *
     * @param competition
     * @return By default it returns all Standings with type TOTAL
     */
    public List<Standing> getStandingsByCompetition(Competition competition) {
        return getStandingsByCompetition(competition,defaultStandingsType);
    }

    public List<Standing> getStandingsByCompetition(Competition competition, String standingsType) {
        List<Standing> standings = standingRepository
                .findAllByCompetitionAndType(
                        competition,
                        Optional.of(standingsType).orElse(defaultStandingsType));
        return standings;
    }


}
