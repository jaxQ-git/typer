package pl.most.typer.service.footballservice;

import org.springframework.http.HttpStatus;

public interface LeagueService {


    HttpStatus getStandingInfoFromExternalApi(Integer leagueId);
}
