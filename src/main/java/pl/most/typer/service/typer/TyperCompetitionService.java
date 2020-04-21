package pl.most.typer.service.typer;

import pl.most.typer.exceptions.ResourceNotFoundException;
import pl.most.typer.model.typer.TyperCompetition;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TyperCompetitionService {

    TyperCompetition save(TyperCompetition typerCompetition);

    TyperCompetition findById(Integer id) throws ResourceNotFoundException;

    List<TyperCompetition> findAll(int pageNumber, int rowPerPage);

    void update(TyperCompetition typerCompetition);

    void deleteById(Integer id) throws ResourceNotFoundException;

    Long count();
}
