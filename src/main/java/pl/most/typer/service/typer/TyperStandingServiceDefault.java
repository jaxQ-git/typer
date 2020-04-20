package pl.most.typer.service.typer;

import org.springframework.stereotype.Service;
import pl.most.typer.model.typer.TyperCompetition;
import pl.most.typer.model.typer.TyperStanding;
import pl.most.typer.repository.typerrepo.TyperStandingRepository;

import java.util.List;

@Service
public class TyperStandingServiceDefault implements TyperStandingService {

    TyperStandingRepository typerStandingRepository;

    public TyperStandingServiceDefault(TyperStandingRepository typerStandingRepository) {
        this.typerStandingRepository = typerStandingRepository;
    }

    @Override
    public List<TyperStanding> findAllByCompetitionId(Integer id) {
        TyperCompetition typerCompetition = new TyperCompetition();
        typerCompetition.setId(id);
        return typerStandingRepository.findAllByTyperCompetition(typerCompetition);
    }
}
