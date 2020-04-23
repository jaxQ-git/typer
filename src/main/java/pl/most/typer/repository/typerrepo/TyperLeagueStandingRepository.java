package pl.most.typer.repository.typerrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.most.typer.model.typer.TyperLeagueStanding;
import pl.most.typer.model.typer.TyperPlayer;
import pl.most.typer.model.typer.TyperStanding;

import java.util.List;

public interface TyperLeagueStandingRepository extends JpaRepository<TyperLeagueStanding, Integer> {

    List<TyperLeagueStanding> findAllByTyperStanding(TyperStanding typerStanding);

}
