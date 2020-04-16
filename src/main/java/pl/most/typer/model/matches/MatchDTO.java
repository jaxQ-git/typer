package pl.most.typer.model.matches;

import lombok.Data;
import pl.most.typer.model.competition.Competition;

import java.util.List;

@Data
public class MatchDTO {

    private Competition competition;

    private List<Match> matches;

}
