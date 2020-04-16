package pl.most.typer.model.competition;

import lombok.Data;

import java.util.List;

@Data
public class CompetitionDTO {

    private Competition competition;

    private Season season;

    private List<Standing> standings;

}
