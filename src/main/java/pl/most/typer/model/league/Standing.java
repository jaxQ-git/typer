package pl.most.typer.model.league;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

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
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "standing")
    private List<LeagueStanding> table;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;


}
