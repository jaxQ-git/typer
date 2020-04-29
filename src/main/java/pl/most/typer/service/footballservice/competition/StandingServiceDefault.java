package pl.most.typer.service.footballservice.competition;

import org.springframework.stereotype.Service;
import pl.most.typer.exceptions.ResourceAlreadyExistsException;
import pl.most.typer.exceptions.ResourceNotFoundException;
import pl.most.typer.model.competition.Competition;
import pl.most.typer.model.competition.Season;
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
    public boolean existsById(Long id) {
        return standingRepository.existsById(id);
    }

    private Standing findById(Long id) {
        Optional<Standing> standingOptional = standingRepository.findById(id);
        return standingOptional.orElseThrow(() -> {
            ResourceNotFoundException ex = new ResourceNotFoundException("Cannot find Standing with id: " + id);
            ex.setResource("standing");
            return ex;
        });
    }

    @Override
    public Standing save(Standing standing) {
        if (standing.getId()==null || !existsById(standing.getId())) {
            return standingRepository.save(standing);
        }
        else {
            ResourceAlreadyExistsException ex = new ResourceAlreadyExistsException("Standing with id: " + standing.getId()
            );
            ex.setResource("Standing");
            ex.setIssue("id");
            throw ex;
        }
    }



    @Override
    public void saveOrUpdateAll(List<Standing> standings) {
        //before save new standings from external Api delete all existing
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
    @Override
    public List<Standing> getStandingsByCompetition(Competition competition) {
        return getStandingsByCompetition(competition, defaultStandingsType);
    }

    @Override
    public List<Standing> getStandingsByCompetition(Competition competition, String standingsType) {
        List<Standing> standings = standingRepository
                .findAllByCompetitionAndType(
                        competition,
                        Optional.of(standingsType).orElse(defaultStandingsType));
        return standings;
    }

    @Override
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
