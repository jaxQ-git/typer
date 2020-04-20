package pl.most.typer.service.footballservice.competition;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.most.typer.model.competition.*;
import pl.most.typer.repository.footballrepo.StandingRepository;
import pl.most.typer.service.footballservice.FootballApiService;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class LeagueServiceDefault implements LeagueService {

    private TeamService teamService;
    private SeasonService seasonService;
    private CompetitionService competitionService;
    private StandingService standingService;
    private FootballApiService footballApiService;


    public LeagueServiceDefault(StandingRepository standingRepository, TeamService teamService, SeasonService seasonService, CompetitionService competitionService, RestTemplate restTemplate, StandingService standingService, FootballApiService footballApiService) {
        this.teamService = teamService;
        this.seasonService = seasonService;
        this.competitionService = competitionService;
        this.standingService = standingService;
        this.footballApiService = footballApiService;
    }

    @Override
    public HttpStatus getStandingInfoFromExternalApi(Integer leagueId) {
        List<String> endpoints = Arrays.asList("competitions", leagueId.toString(), "standings");
        Map<String, String> filters = new HashMap<>();
        ResponseEntity<CompetitionDTO> entity = footballApiService
                .getExternalData(endpoints,filters,CompetitionDTO.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            CompetitionDTO competitionDTO = entity.getBody();
            if (competitionDTO != null) {
                Competition savedCompetition = competitionService.save(competitionDTO.getCompetition());
                boolean isCompetitionUpdated = !savedCompetition.getLastUpdated().isEqual(competitionDTO.getCompetition().getLastUpdated());
                setChildFieldInCompetitionDTO(competitionDTO, savedCompetition);
                teamService.saveAll(getAllTeamsFromCompetitionDTO(competitionDTO));
                seasonService.save(competitionDTO.getSeason());
                standingService.saveAll(competitionDTO.getStandings());

                return HttpStatus.CREATED;
            } else {
                return HttpStatus.BAD_GATEWAY;
            }
        }
        return HttpStatus.BAD_GATEWAY;
    }

    private boolean checkIfExternalCompetitionIsRecent(Competition ExtCompetition, Competition competitionFromDB) {
        return ExtCompetition.getLastUpdated().isEqual(competitionFromDB.getLastUpdated());
    }

    private void setChildFieldInCompetitionDTO(CompetitionDTO competitionDTO, Competition competition) {
        competitionDTO.setCompetition(competition);
        seasonService.setCompetitionInSeasons(Arrays.asList(competitionDTO.getSeason()),competition);
        standingService.setCompetitionInStandings(competitionDTO.getStandings(), competition);
        standingService.setStandingInLeagueStanding(competitionDTO.getStandings());
    }



    private List<Team> getAllTeamsFromCompetitionDTO(CompetitionDTO competitionDTO) {
        return competitionDTO.getStandings()
                .stream()
                .map(Standing::getLeagueStandings)
                .flatMap(Collection::stream)
                .map(LeagueStanding::getTeam)
                .collect(Collectors.toList());
    }



}
