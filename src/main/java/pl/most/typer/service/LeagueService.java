package pl.most.typer.service;

import org.springframework.http.HttpStatus;

public interface LeagueService {


    HttpStatus getStandingInfoFromExternalApi(Integer leagueId);
}
