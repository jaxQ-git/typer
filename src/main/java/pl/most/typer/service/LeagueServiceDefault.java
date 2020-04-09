package pl.most.typer.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.most.typer.model.league.*;
import pl.most.typer.repository.SeasonRepository;
import pl.most.typer.repository.StandingRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LeagueServiceDefault implements LeagueService {

    private StandingRepository standingRepository;
    private TeamService teamService;
    private SeasonRepository seasonRepository;
    private CompetitionService competitionService;


    public LeagueServiceDefault(StandingRepository standingRepository, TeamService teamService, SeasonRepository seasonRepository, CompetitionService competitionService) {
        this.standingRepository = standingRepository;
        this.teamService = teamService;
        this.seasonRepository = seasonRepository;
        this.competitionService = competitionService;
    }

    @Override
    public void getStandingInfoFromExternalApi(Integer leagueId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CompetitionDTO> entity = restTemplate.exchange(
                "https://api.football-data.org/v2/competitions/" + leagueId + "/standings",
                HttpMethod.GET,
                getStringHttpEntity(),
                CompetitionDTO.class
        );
        if (entity.getStatusCode().is2xxSuccessful()) {
            CompetitionDTO competitionDTO = entity.getBody();
            Integer apiId = competitionDTO.getCompetition().getApiId();
            if (competitionService.existsCompetitionByApiId(apiId)) {
                return;
                //TODO update the information
            }
            Competition competition = competitionDTO.getCompetition();
            competitionDTO.getSeason().setCompetition(competition);
            competitionDTO.getStandings().forEach(standing -> standing.setCompetition(competition));
            setStandingInLeagueStanding(competitionDTO);

            List<Team> distinctTeams = getDistinctTeams(competitionDTO);
            distinctTeams.forEach(team -> teamService.saveTeam(team));

            competitionService.save(competition);
            competitionDTO.getStandings().forEach(standing -> standingRepository.save(standing));
            seasonRepository.save(competitionDTO.getSeason());


        }



    }

    private List<Team> getDistinctTeams(CompetitionDTO competitionDTO) {
        return competitionDTO.getStandings()
                .stream()
                .filter(standing -> standing.getType().equals("TOTAL"))
                .map(standing -> standing.getLeagueStandings())
                .flatMap(leagueStandings -> leagueStandings.stream())
                .map(leagueStanding -> leagueStanding.getTeam())
                .distinct()
                .collect(Collectors.toList());
    }

    private void setStandingInLeagueStanding(CompetitionDTO competitionDTO) {
        competitionDTO.getStandings().forEach(standing -> {
            standing.getLeagueStandings().forEach(leagueStanding -> leagueStanding.setStanding(standing));
        });
    }

    private HttpEntity<String> getStringHttpEntity() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-Auth-Token","d6d4a40948f344e78fd1b8a461c4d213");
        return new HttpEntity<>(httpHeaders);
    }

}
