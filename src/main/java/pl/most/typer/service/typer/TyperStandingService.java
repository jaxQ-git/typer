package pl.most.typer.service.typer;

import pl.most.typer.model.typer.TyperStanding;

import java.util.List;

public interface TyperStandingService {

    List<TyperStanding> findAllByCompetitionId(Integer id);
}
