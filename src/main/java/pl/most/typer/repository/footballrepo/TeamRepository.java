package pl.most.typer.repository.footballrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.most.typer.model.competition.Team;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team,Long> {

    boolean existsByApiId(Integer apiId);

    Optional<Team> findByApiId(Integer apiId);
}
