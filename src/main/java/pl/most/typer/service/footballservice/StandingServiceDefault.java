package pl.most.typer.service.footballservice;

import org.springframework.stereotype.Service;
import pl.most.typer.model.league.Competition;
import pl.most.typer.model.league.Standing;
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
        if (standing.getId() != null) {
            Optional<Standing> optionalStanding = standingRepository.findById(standing.getId());
            if (optionalStanding.isEmpty()) {
                standingRepository.save(standing);
            } else {
                Standing standingDB = optionalStanding.get();
                if (!standingDB.getCompetition().getLastUpdated().isEqual(standing.getCompetition().getLastUpdated())) {
                    standingDB.setGroupName(standing.getGroupName());
                    standingDB.setType(standing.getType());
                    standingDB.setStage(standing.getStage());
                    leagueStandingRepository.deleteAll(standingDB.getLeagueStandings());
                    standingDB.setLeagueStandings(standing.getLeagueStandings());
                    standingDB.setCompetition(standing.getCompetition());
                    standingDB = standingRepository.save(standingDB);
                }
                return standingDB;
            }
        }
        return standingRepository.save(standing);
    }

    @Override
    public void saveAll(List<Standing> standings) {
        standings.forEach(this::save);
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


}
