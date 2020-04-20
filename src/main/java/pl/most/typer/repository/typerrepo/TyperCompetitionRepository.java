package pl.most.typer.repository.typerrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.most.typer.model.typer.TyperCompetition;

public interface TyperCompetitionRepository extends JpaRepository<TyperCompetition,Integer> {

}
