package pl.most.typer.repository.typerrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.most.typer.model.typer.TyperCompetition;

import java.util.Optional;

public interface TyperCompetitionRepository extends JpaRepository<TyperCompetition,Integer> {

    boolean existsByName(String name);

    Optional<TyperCompetition> findByName(String name);
}
