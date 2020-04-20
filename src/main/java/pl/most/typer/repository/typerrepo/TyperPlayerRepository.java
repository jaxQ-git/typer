package pl.most.typer.repository.typerrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.most.typer.model.typer.TyperPlayer;

public interface TyperPlayerRepository extends JpaRepository<TyperPlayer, Long> {
}
