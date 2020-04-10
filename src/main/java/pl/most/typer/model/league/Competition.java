package pl.most.typer.model.league;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competition", fetch = FetchType.LAZY)
    private List<Season> seasons;



    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "competition")
    private List<Standing> standings;

}
