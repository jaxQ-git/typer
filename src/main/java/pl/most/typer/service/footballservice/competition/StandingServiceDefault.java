package pl.most.typer.service.footballservice.competition;

import org.springframework.stereotype.Service;
import pl.most.typer.model.competition.Competition;
import pl.most.typer.model.competition.Standing;
import pl.most.typer.repository.footballrepo.LeagueStandingRepository;
import pl.most.typer.repository.footballrepo.StandingRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
///
public class StandingServiceDefault implements StandingService {

    private final String defaultStandingsType = "TOTAL";
    private StandingRepository standingRepository;
    private LeagueStandingRepository leagueStandingRepository;

    public StandingServiceDefault(StandingRepository standingRepository) {
        this.standingRepository = standingRepository;
    }


    @Override
    public Standing save(Standing standing) {
        return standingRepository.save(standing);
    }

    @Override
    public void saveAll(List<Standing> standings) {
        List<Standing> standingDB = standings.stream()
                .map(standing -> standing.getCompetition())
                .distinct()
                .map(competition -> getAllStandingsByCompetition(competition))
                .flatMap(standingList -> standingList.stream())
                .collect(Collectors.toList());
        standingRepository.deleteAll(standingDB);
        standings.forEach(this::save);
    }


    /**
     * @param competition
     * @return By default it returns all Standings with type TOTAL
     */
    private List<Standing> getAllStandingsByCompetition(Competition competition) {
        return standingRepository.findAllByCompetition(competition);
    }

    /**
     * @param competition
     * @return By default it returns all Standings with type TOTAL
     */
    public List<Standing> getStandingsByCompetition(Competition competition) {
        return getStandingsByCompetition(competition, defaultStandingsType);
    }

    public List<Standing> getStandingsByCompetition(Competition competition, String standingsType) {
        List<Standing> standings = standingRepository
                .findAllByCompetitionAndType(
                        competition,
                        Optional.of(standingsType).orElse(defaultStandingsType));
        return standings;
    }

    public void setStandingInLeagueStanding(List<Standing> standings) {
        standings.forEach(standing -> {
            standing.getLeagueStandings().forEach(leagueStanding -> leagueStanding.setStanding(standing));
        });
    }

    @Override
    public void setCompetitionInStandings(List<Standing> standings, Competition competition) {
        standings.forEach(standing -> standing.setCompetition(competition));
    }

    @Override
    public Optional<Standing> findFirstByCompetiton(Competition competition) {
        return standingRepository.findFirstByCompetition(competition);
    }


}
