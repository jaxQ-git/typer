package pl.most.typer.service.typer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.most.typer.exceptions.BadResourceException;
import pl.most.typer.exceptions.ResourceAlreadyExistsException;
import pl.most.typer.exceptions.ResourceNotFoundException;
import pl.most.typer.model.typer.TyperCompetition;
import pl.most.typer.repository.typerrepo.TyperCompetitionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TyperCompetitionServiceDefault implements TyperCompetitionService {

    TyperCompetitionRepository typerCompetitionRepository;

    public TyperCompetitionServiceDefault(TyperCompetitionRepository typerCompetitionRepository) {
        this.typerCompetitionRepository = typerCompetitionRepository;
    }




    private boolean existsById(Integer id) {
        return typerCompetitionRepository.existsById(id);
    }

    @Override
    public TyperCompetition findById(Integer id) throws ResourceNotFoundException {
        Optional<TyperCompetition> typerCompetitionOptional = typerCompetitionRepository.findById(id);
        return typerCompetitionOptional.orElseThrow(() -> new ResourceNotFoundException("Cannot find TyperCompetition with id: " + id));
    }

    @Override
    public List<TyperCompetition> findAll(int pageNumber, int rowPerPage) {
        List<TyperCompetition> typerCompetitions = new ArrayList<>();
        Pageable sortedByIdAsc = PageRequest.of(pageNumber - 1, rowPerPage,
                Sort.by("id").ascending());
        typerCompetitionRepository.findAll(sortedByIdAsc).forEach(typerCompetitions::add);
        return typerCompetitions;
    }

    @Override
    public TyperCompetition save(TyperCompetition typerCompetition) throws BadResourceException, ResourceAlreadyExistsException {
        if (!StringUtils.isEmpty(typerCompetition.getName())) {
            if (typerCompetition.getId() != null && existsById(typerCompetition.getId())) {
                throw new ResourceAlreadyExistsException("TyperCompetition with id: " + typerCompetition.getId() +
                        " already exists");
            }
            return typerCompetitionRepository.save(typerCompetition);
        }
        else {
            BadResourceException exc = new BadResourceException("Failed to save typerCompetition");
            throw exc;
        }
    }

    @Override
    public void update(TyperCompetition typerCompetition)
            throws BadResourceException, ResourceNotFoundException {
        if (!StringUtils.isEmpty(typerCompetition.getName())) {
            if (!existsById(typerCompetition.getId())) {
                throw new ResourceNotFoundException("Cannot find TyperCompetition with id: " + typerCompetition.getId());
            }
            typerCompetitionRepository.save(typerCompetition);
        }
        else {
            BadResourceException exc = new BadResourceException("Failed to save typerCompetition");
            throw exc;
        }
    }

    @Override
    public void deleteById(Integer id) throws ResourceNotFoundException {
        if (!existsById(id)) {
            throw new ResourceNotFoundException("Cannot find typerCompetition with id: " + id);
        }
        else {
            typerCompetitionRepository.deleteById(id);
        }
    }

    @Override
    public Long count() {
        return typerCompetitionRepository.count();
    }
}
