package pl.most.typer.model.league;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    @JsonProperty("id")
    private Integer apiId;

    @ManyToOne
    private Area area;

    private String name;

    private String code;

    private String plan;

    private String lastUpdated;


}
