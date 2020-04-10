package pl.most.typer.service.footballservice;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FootballApiService {

    String BASIC_URL = "https://api.football-data.org/v2";
    <T> ResponseEntity<T> getExternalData(List<String> endpoints, Class<T> returnedEntity);
}
