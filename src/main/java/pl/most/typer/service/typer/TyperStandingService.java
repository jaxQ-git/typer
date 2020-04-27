package pl.most.typer.service.typer;

import pl.most.typer.exceptions.BadResourceException;
import pl.most.typer.exceptions.ResourceAlreadyExistsException;
import pl.most.typer.exceptions.ResourceNotFoundException;
import pl.most.typer.model.typer.TyperCompetition;
import pl.most.typer.model.typer.TyperPlayer;
import pl.most.typer.model.typer.TyperStanding;

import java.util.List;

public interface TyperStandingService {

    List<TyperStanding> findAllByTyperCompetitionId(Integer id);

    TyperStanding findLatestStandingByTyperCompetition(TyperCompetition typerCompetition) throws ResourceNotFoundException;

    TyperStanding findStandingByTyperCompetition(TyperCompetition typerCompetition, Integer round) throws ResourceNotFoundException;

    void deleteAll(List<TyperStanding> typerStandings);

    void saveAll(List<TyperStanding> standings);

    void createNewTyperStanding(TyperCompetition typerCompetition, TyperStanding latestStandingByTyperCompetition);

    TyperStanding save(TyperStanding typerStanding) throws BadResourceException, ResourceAlreadyExistsException;

    TyperStanding update(TyperStanding typerStanding);
}
