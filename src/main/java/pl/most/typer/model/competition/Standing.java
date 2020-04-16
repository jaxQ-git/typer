package pl.most.typer.model.competition;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Standing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String stage;
    private String type;
    @JsonProperty("group")
    private String groupName;
    @ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "standing")
    @JsonProperty("table")
    @OrderBy("position ASC")
    private List<LeagueStanding> leagueStandings;
    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;



}
