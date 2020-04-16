package pl.most.typer.model.competition;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Area {


    @JsonProperty("id")
    @Id
    private Integer apiId;
    private String name;
}
