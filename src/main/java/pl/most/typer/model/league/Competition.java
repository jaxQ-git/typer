package pl.most.typer.model.league;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    private String lastUpdated;

    public Competition(Integer apiId, String name) {
        this.apiId = apiId;
        this.name = name;
    }

    //    private String emblemUrl;
//
//    private Integer numberOfAvailableSeasons;


}
