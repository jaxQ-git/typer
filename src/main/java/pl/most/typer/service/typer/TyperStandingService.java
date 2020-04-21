package pl.most.typer.service.typer;

import pl.most.typer.model.typer.TyperCompetition;
import pl.most.typer.model.typer.TyperStanding;

import java.util.List;
import java.util.Optional;

public interface TyperStandingService {

    List<TyperStanding> findAllByTyperCompetitionId(Integer id);

    Optional<TyperStanding> findLatestStandingByTyperCompetition(TyperCompetition typerCompetition);

    Optional<TyperStanding> findStandingByTyperCompetition(TyperCompetition typerCompetition, Integer round);

}
