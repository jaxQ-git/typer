package pl.most.typer.service.footballservice.competition;

import org.springframework.http.HttpStatus;

public interface LeagueService {


    HttpStatus getStandingInfoFromExternalApi(Integer leagueId);
}
