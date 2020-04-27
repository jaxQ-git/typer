package pl.most.typer.service.typer;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.most.typer.exceptions.BadResourceException;
import pl.most.typer.exceptions.ResourceAlreadyExistsException;
import pl.most.typer.exceptions.ResourceNotFoundException;
import pl.most.typer.model.typer.TyperCompetition;
import pl.most.typer.model.typer.TyperStanding;
import pl.most.typer.repository.typerrepo.TyperStandingRepository;
import pl.most.typer.service.footballservice.competition.StandingService;

import java.util.List;
import java.util.Optional;

@Service
public class TyperStandingServiceDefault implements TyperStandingService {

    private TyperStandingRepository typerStandingRepository;

    public TyperStandingServiceDefault(TyperStandingRepository typerStandingRepository) {
        this.typerStandingRepository = typerStandingRepository;
    }

    @Override
    public List<TyperStanding> findAllByTyperCompetitionId(Integer id) {
        TyperCompetition typerCompetition = new TyperCompetition();
        typerCompetition.setId(id);
        return typerStandingRepository.findAllByTyperCompetition(typerCompetition);
    }

    @Override
    public TyperStanding findLatestStandingByTyperCompetition(TyperCompetition typerCompetition) throws ResourceNotFoundException {
        return findStandingByTyperCompetition(typerCompetition, typerCompetition.getCurrentRound());
    }

    @Override
    public TyperStanding findStandingByTyperCompetition(TyperCompetition typerCompetition, Integer round) throws ResourceNotFoundException{
        Optional<TyperStanding> optionalTyperStanding = typerStandingRepository.findByTyperCompetitionAndRound(typerCompetition, round);
        if (optionalTyperStanding.isEmpty()) {
            ResourceNotFoundException ex = new ResourceNotFoundException();
            ex.setResource("standing");
            throw ex;
        }
        return optionalTyperStanding.get();
    }


    @Override
    public void deleteAll(List<TyperStanding> typerStandings) {
        typerStandingRepository.deleteAll(typerStandings);
    }

    @Override
    public void saveAll(List<TyperStanding> standings) {
        typerStandingRepository.saveAll(standings);
    }

    @Override
    public void createNewTyperStanding(TyperCompetition typerCompetition, TyperStanding latestStandingByTyperCompetition) {
        TyperStanding typerStanding = new TyperStanding(typerCompetition);
        //TODO dodac punkty z poprzedniej kolejki
        save(typerStanding);
    }


    public TyperStanding save(TyperStanding typerStanding) throws BadResourceException, ResourceAlreadyExistsException {
        if (typerStanding.getTyperCompetition()!=null) {
            if (typerStanding.getId() != null && existsById(typerStanding.getId())) {
                ResourceAlreadyExistsException ex = new ResourceAlreadyExistsException("TyperStanding with id: " + typerStanding.getId());
                ex.setResource("TyperStanding");
                ex.setIssue("id");
                throw ex;
            }
            return typerStandingRepository.save(typerStanding);
        }
        else {
            BadResourceException exc = new BadResourceException("Failed to save TyperCompetition");
            throw exc;
        }
    }

    private boolean existsById(Integer id) {
        return typerStandingRepository.existsById(id);
    }


}

