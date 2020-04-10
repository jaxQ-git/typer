package pl.most.typer.service.footballservice;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FootballApiServiceDefault implements FootballApiService {

    private final String API_TOKEN = "d6d4a40948f344e78fd1b8a461c4d213";
    private final String TypeOfToken = "X-Auth-Token";

    public FootballApiServiceDefault(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private RestTemplate restTemplate;

    public <T> ResponseEntity<T> getExternalData(List<String> endpoints, Class<T> returnedEntity) {

        ResponseEntity<T> entity = restTemplate.exchange(
                prepareURL(endpoints),
                HttpMethod.GET,
                getStringHttpEntity(),
                returnedEntity
        );
        return entity;
    }

    private String prepareURL(List<String> endpoints){
        StringBuilder stringBuilder = new StringBuilder().append(BASIC_URL);
        for (String endpoint : endpoints) {
            stringBuilder.append("/").append(endpoint);
        }
        return stringBuilder.toString();
    }
    private HttpEntity<String> getStringHttpEntity() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(TypeOfToken, API_TOKEN);
        return new HttpEntity<>(httpHeaders);
    }
}
