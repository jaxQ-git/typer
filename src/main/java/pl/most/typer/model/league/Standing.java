package pl.most.typer.model.league;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Standing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String stage;
    private String type;
    private String group;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "standing")
    private List<LeagueStanding> table;


}
