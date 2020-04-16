package pl.most.typer.model.competition;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Data
@Entity
public class Team {

    @Id
    @JsonProperty("id")
    private Integer apiId;
    private String name;
    private String crestUrl;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return apiId.equals(team.apiId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apiId);
    }
}
