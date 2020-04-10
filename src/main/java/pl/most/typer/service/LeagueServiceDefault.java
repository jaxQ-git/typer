package pl.most.typer.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.most.typer.model.league.*;
import pl.most.typer.repository.SeasonService;
import pl.most.typer.repository.StandingRepository;

import java.util.Arrays;
import java.util.List;
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
        ResponseEntity<CompetitionDTO> entity = footballApiService.getExternalData(Arrays.asList("competitions",leagueId.toString(),"standings"),CompetitionDTO.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            CompetitionDTO competitionDTO = entity.getBody();
            if (competitionService.existsCompetitionByApiId(competitionDTO.getCompetition().getApiId())) {
                return HttpStatus.OK;
                //TODO update the information
            }

            setChildFieldInCompetitionDTO(competitionDTO);
            teamService.saveTeams(getAllTeamsFromCompetitionDTO(competitionDTO));
            competitionService.save(competitionDTO.getCompetition());
            standingService.saveAll(competitionDTO.getStandings());
            seasonService.save(competitionDTO.getSeason());

            return HttpStatus.CREATED;
        }
        else{
            return HttpStatus.BAD_GATEWAY;
        }



    }

    private void setChildFieldInCompetitionDTO(CompetitionDTO competitionDTO) {
        competitionDTO.getSeason().setCompetition(competitionDTO.getCompetition());
        competitionDTO.getStandings().forEach(standing -> standing.setCompetition(competitionDTO.getCompetition()));
        setStandingInLeagueStanding(competitionDTO);
    }



    private void setStandingInLeagueStanding(CompetitionDTO competitionDTO) {
        competitionDTO.getStandings().forEach(standing -> {
            standing.getLeagueStandings().forEach(leagueStanding -> leagueStanding.setStanding(standing));
        });
    }


    private List<Team> getAllTeamsFromCompetitionDTO(CompetitionDTO competitionDTO) {
        return competitionDTO.getStandings()
                .stream()
                .map(standing -> standing.getLeagueStandings())
                .flatMap(leagueStandings -> leagueStandings.stream())
                .map(leagueStanding -> leagueStanding.getTeam())
                .collect(Collectors.toList());
    }



}
