package pl.most.typer.configuration;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "extapi.football")
@Data
@NoArgsConstructor
public class ExternalFootballApiProps {

    private String apiToken;
    private String typeOfToken;
    private String basicUrl;

}
