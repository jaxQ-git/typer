package pl.most.typer.service.typer;

import org.springframework.stereotype.Service;
import pl.most.typer.model.typer.TyperCompetition;
import pl.most.typer.repository.typerrepo.TyperCompetitionRepository;

import java.util.Optional;

@Service
public class TyperCompetitionServiceDefault implements TyperCompetitionService {

    TyperCompetitionRepository typerCompetitionRepository;

    public TyperCompetitionServiceDefault(TyperCompetitionRepository typerCompetitionRepository) {
        this.typerCompetitionRepository = typerCompetitionRepository;
    }

    @Override
    public TyperCompetition save(TyperCompetition typerCompetition) {
        return typerCompetitionRepository.save(typerCompetition);
    }

    @Override
    public Optional<TyperCompetition> findById(Integer id) {
        return typerCompetitionRepository.findById(id);
    }
}
