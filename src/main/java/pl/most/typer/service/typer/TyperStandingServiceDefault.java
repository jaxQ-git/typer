package pl.most.typer.service.typer;

import org.springframework.stereotype.Service;
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
    public void saveDefaultStanding(TyperCompetition typerCompetition) {
        TyperStanding typerStanding = new TyperStanding();
        typerStanding.setTyperCompetition(typerCompetition);
        typerStandingRepository.save(typerStanding);
    }

    @Override
    public void deleteAll(List<TyperStanding> typerStandings) {
        typerStandingRepository.deleteAll(typerStandings);
    }


}

