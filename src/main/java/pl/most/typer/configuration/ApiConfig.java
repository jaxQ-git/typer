package pl.most.typer.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {
    public static final String COMPETITIONS_URL = "https://api.football-data.org/v2/competitions/";
    public static final String HEADER_VALUE = "d6d4a40948f344e78fd1b8a461c4d213";
    public static final String HEADER_NAME = "X-Auth-Token";

}
