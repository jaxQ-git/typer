package pl.most.typer.model.competition;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Objects;

@Data
@Entity
public class Season {

    @Id
    @JsonProperty("id")
    private Integer apiId;

    private LocalDate startDate;
    private LocalDate endDate;

    private Integer currentMatchday;

    @ManyToOne
    private Team winner;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Season season = (Season) o;
        return Objects.equals(apiId, season.apiId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apiId);
    }
}
