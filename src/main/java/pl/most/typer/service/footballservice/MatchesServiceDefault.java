package pl.most.typer.service.footballservice;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.most.typer.configuration.ApiConfig;
import pl.most.typer.model.league.Competition;
import pl.most.typer.model.league.Season;
import pl.most.typer.model.league.Team;
import pl.most.typer.model.matches.Match;
import pl.most.typer.model.matches.MatchDTO;
import pl.most.typer.model.matches.Score;
import pl.most.typer.model.matches.TeamGoals;
import pl.most.typer.repository.footballrepo.MatchesRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MatchesServiceDefault implements MatchesService {

    private final CompetitionService competitionService;
    private final TeamService teamService;
    private final SeasonService seasonService;
    private final ScoreService scoreService;
    private final TeamGoalsService teamGoalsService;
    private final MatchesRepository matchesRepository;


    public MatchesServiceDefault(CompetitionService competitionService, TeamService teamService, SeasonService seasonService, ScoreService scoreService, TeamGoalsService teamGoalsService, MatchesRepository matchesRepository) {
        this.competitionService = competitionService;
        this.teamService = teamService;
        this.seasonService = seasonService;
        this.scoreService = scoreService;
        this.teamGoalsService = teamGoalsService;
        this.matchesRepository = matchesRepository;
    }


    @Override
    public void getMatchesByCompetitionId(Integer competitionId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MatchDTO> responseEntity = restTemplate.exchange(
                ApiConfig.COMPETITIONS_URL + competitionId + "/matches",
                HttpMethod.GET,
                getStringHttpEntity(),
                MatchDTO.class
        );
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            MatchDTO matchDTO = responseEntity.getBody();
            if (matchDTO != null) {
                Competition competition = competitionService.save(matchDTO.getCompetition());
                setCompetitionForMatches(matchDTO, competition);
                setCompetitionForSeasons(matchDTO, competition);

                seasonService.save(getSeasonsFromMatchDTO(matchDTO));
                teamService.saveTeams(getTeamsFromMatchDTO(matchDTO));
                teamGoalsService.save(getTeamGoalsFromMatchDTO(matchDTO));
                scoreService.save(getScoreFromMatchDTO(matchDTO));

                save(matchDTO.getMatches());
            }
        }
    }


    @Override
    public void save(List<Match> matches) {
        for (Match matchList : matches) {
            Optional<Match> byApiId = matchesRepository.findByApiId(matchList.getApiId());
            byApiId.orElseGet(() -> matchesRepository.save(matchList));
        }
    }

    private void setCompetitionForMatches(MatchDTO matchDTO, Competition competition) {
        matchDTO.getMatches().forEach(match -> match.setCompetition(competition));
    }

    private void setCompetitionForSeasons(MatchDTO matchDTO, Competition competition) {
        getSeasonsFromMatchDTO(matchDTO).forEach(season -> season.setCompetition(competition));
    }


    //zeby dzialalo distinct potrzebne jest nadpisanie metody equals w klasie season
    public List<Season> getSeasonsFromMatchDTO(MatchDTO matchDTO) {
        return matchDTO.getMatches()
                .stream()
                .map(Match::getSeason)
                .distinct()
                .collect(Collectors.toList());
    }

    private List<Team> getTeamsFromMatchDTO(MatchDTO matchDTO) {
        return matchDTO.getMatches().stream()
                .flatMap(match -> Stream.of(match.getAwayTeam(), match.getHomeTeam()))
                .collect(Collectors.toList());
    }

    private List<Score> getScoreFromMatchDTO(MatchDTO matchDTO) {
        return matchDTO.getMatches()
                .stream()
                .map(Match::getScore)
                .collect(Collectors.toList());
    }

    private List<TeamGoals> getTeamGoalsFromMatchDTO(MatchDTO matchDTO) {
        return matchDTO.getMatches()
                .stream()
                .map(Match::getScore)
                .flatMap(score -> Stream.of(score.getExtraTime(), score.getFullTime()
                        , score.getHalfTime(), score.getPenalties()))
                .collect(Collectors.toList());
    }


    private HttpEntity<String> getStringHttpEntity() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(ApiConfig.HEADER_NAME, ApiConfig.HEADER_VALUE);
        return new HttpEntity<>(httpHeaders);
    }


}
