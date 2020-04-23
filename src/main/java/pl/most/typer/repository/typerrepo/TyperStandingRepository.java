package pl.most.typer.repository.typerrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.most.typer.model.typer.TyperCompetition;
import pl.most.typer.model.typer.TyperPlayer;
import pl.most.typer.model.typer.TyperStanding;

import java.util.List;
import java.util.Optional;

public interface TyperStandingRepository extends JpaRepository<TyperStanding, Integer> {

    List<TyperStanding> findAllByTyperCompetition(TyperCompetition Id);

    Optional<TyperStanding> findByTyperCompetitionAndRound(TyperCompetition typerCompetition, Integer round);
}
