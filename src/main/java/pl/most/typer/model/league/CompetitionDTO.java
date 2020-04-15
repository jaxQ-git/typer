package pl.most.typer.model.league;

import lombok.Data;
import pl.most.typer.model.league.Competition;
import pl.most.typer.model.league.Season;
import pl.most.typer.model.league.Standing;

import java.util.List;

@Data
public class CompetitionDTO {

    private Competition competition;

    private Season season;

    private List<Standing> standings;

}
