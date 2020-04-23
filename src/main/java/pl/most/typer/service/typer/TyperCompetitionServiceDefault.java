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
    TyperStandingService typerStandingService;

    public TyperCompetitionServiceDefault(TyperCompetitionRepository typerCompetitionRepository,
                                          TyperStandingService typerStandingService) {
        this.typerCompetitionRepository = typerCompetitionRepository;
        this.typerStandingService = typerStandingService;
    }

    private boolean existsById(Integer id) {
        return typerCompetitionRepository.existsById(id);
    }

    private boolean existsByName(String name) {
        return typerCompetitionRepository.existsByName(name);
    }

    @Override
    public TyperCompetition findById(Integer id) throws ResourceNotFoundException {
        Optional<TyperCompetition> typerCompetitionOptional = typerCompetitionRepository.findById(id);
        return typerCompetitionOptional.orElseThrow(() -> {
            ResourceNotFoundException ex = new ResourceNotFoundException("Cannot find TyperCompetition with id: " + id);
            ex.setResource("competition");
            return ex;
        });
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
                ResourceAlreadyExistsException ex = new ResourceAlreadyExistsException("TyperCompetition with id: " + typerCompetition.getId() +
                        " and name: " + typerCompetition.getName() + " already exists");
                ex.setIssue("id");
                throw ex;
            }
            else if (existsByName(typerCompetition.getName())){
                ResourceAlreadyExistsException ex = new ResourceAlreadyExistsException("TyperCompetition with name: " + typerCompetition.getName() + " already exists");
                ex.setIssue("name");
                throw ex;
            }
            TyperCompetition competition = typerCompetitionRepository.save(typerCompetition);
            typerStandingService.saveDefaultStanding(competition);
            return competition;
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
                ResourceNotFoundException ex = new ResourceNotFoundException("Cannot find TyperCompetition with id: " + typerCompetition.getId());
                ex.setIssue("id");
                throw ex;
            }

            else if (existsByName(typerCompetition.getName())){
                ResourceAlreadyExistsException ex = new ResourceAlreadyExistsException("TyperCompetition with name: " + typerCompetition.getName() + " already exists");
                ex.setIssue("name");
                throw ex;
            }
            TyperCompetition typerCompetitionDB = typerCompetitionRepository.findById(typerCompetition.getId()).get();
            typerCompetitionDB.setName(typerCompetition.getName());
            typerCompetitionDB.setCurrentRound(typerCompetition.getCurrentRound());
            typerCompetitionDB.setLastUpdated(typerCompetition.getLastUpdated());
            typerCompetitionRepository.save(typerCompetitionDB);
        }
        else {
            BadResourceException exc = new BadResourceException("Failed to save typerCompetition");
            throw exc;
        }
    }

    @Override
    public void deleteById(Integer id) throws ResourceNotFoundException {
        if (!existsById(id)) {
            ResourceNotFoundException ex = new ResourceNotFoundException("Cannot find typerCompetition with id: " + id);
            ex.setIssue("id");
            throw ex;
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
