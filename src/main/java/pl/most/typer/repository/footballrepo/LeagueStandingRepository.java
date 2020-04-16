package pl.most.typer.repository.footballrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.most.typer.model.competition.LeagueStanding;

public interface LeagueStandingRepository extends JpaRepository<LeagueStanding,Long> {

}
