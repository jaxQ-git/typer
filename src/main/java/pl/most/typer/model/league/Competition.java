package pl.most.typer.model.league;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class Competition {


    @JsonProperty("id")
    @Id
    private Integer apiId;

    @ManyToOne(cascade = CascadeType.ALL)
    private Area area;

    private String name;

    private String code;

    private String plan;

    private LocalDateTime lastUpdated;


    //TODO remove
    public Competition(Integer apiId, String name) {
        this.apiId = apiId;
        this.name = name;
    }

    //  private String emblemUrl;
//
//    private Integer numberOfAvailableSeasons;


}
