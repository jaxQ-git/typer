package pl.most.typer.service.footballservice;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.most.typer.configuration.ApiConfig;
import pl.most.typer.model.league.Competition;
import pl.most.typer.model.league.CompetitionDTO;
import pl.most.typer.repository.footballrepo.SeasonRepository;
import pl.most.typer.repository.footballrepo.StandingRepository;

@Service
public class LeagueServiceDefault implements LeagueService {

    private StandingRepository standingRepository;
    private TeamService teamService;
    private SeasonRepository seasonRepository;
    private CompetitionService competitionService;
    private ApiConfig apiConfig;


    public LeagueServiceDefault(StandingRepository standingRepository, TeamService teamService, SeasonRepository seasonRepository, CompetitionService competitionService, ApiConfig apiConfig, ApiConfig apiConfig1) {
        this.standingRepository = standingRepository;
        this.teamService = teamService;
        this.seasonRepository = seasonRepository;
        this.competitionService = competitionService;
        this.apiConfig = apiConfig;
    }

    @Override
    public void getStandingInfoFromExternalApi(Integer leagueId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CompetitionDTO> entity = restTemplate.exchange(
                ApiConfig.COMPETITIONS_URL + leagueId + "/standings",
                HttpMethod.GET,
                getStringHttpEntity(),
                CompetitionDTO.class
        );
        if (entity.getStatusCode().is2xxSuccessful()) {
            CompetitionDTO competitionDTO = entity.getBody();
            Integer apiId = competitionDTO.getCompetition().getApiId();
            if (competitionService.existsCompetitionByApiId(apiId)) {
                //TODO update the information
            }
            Competition competition = competitionDTO.getCompetition();
            competitionDTO.getSeason().setCompetition(competition);
            competitionDTO.getStandings().forEach(standing -> standing.setCompetition(competition));
            setStandingInLeagueStanding(competitionDTO);
            competitionService.save(competition);
            competitionDTO.getStandings().forEach(standing -> standingRepository.save(standing));
            seasonRepository.save(competitionDTO.getSeason());
        }
    }

    private void setStandingInLeagueStanding(CompetitionDTO competitionDTO) {
        competitionDTO.getStandings()
                .stream()
                .forEach(standing -> standing.getTable().forEach(leagueStanding -> leagueStanding.setStanding(standing)));
    }

    private HttpEntity<String> getStringHttpEntity() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(ApiConfig.HEADER_NAME, ApiConfig.HEADER_VALUE);
        return new HttpEntity<>(httpHeaders);
    }
}
