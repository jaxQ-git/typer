package pl.most.typer.model.league;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.security.PrivateKey;

@Data
@Entity
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    @JsonProperty("id")
    private Integer apiId;

    private String startDate;
    private String endDate;

    private Integer currentMatchday;

    @ManyToOne
    private Team winner;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;
}
