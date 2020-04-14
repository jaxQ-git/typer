package pl.most.typer.service.footballservice;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface FootballApiService {


    <T> ResponseEntity<T> getExternalData(List<String> endpoints, Map<String,String> filters, Class<T> returnedEntity);
}
