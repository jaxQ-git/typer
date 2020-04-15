package pl.most.typer.repository.footballrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.most.typer.model.league.LeagueStanding;

public interface LeagueStandingRepository extends JpaRepository<LeagueStanding,Long> {

}
