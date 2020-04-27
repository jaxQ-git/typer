package pl.most.typer.repository.typerrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.most.typer.model.account.User;
import pl.most.typer.model.typer.TyperPlayer;

import java.util.Optional;

public interface TyperPlayerRepository extends JpaRepository<TyperPlayer, Integer> {
    boolean existsByUser(User user);

    Optional<TyperPlayer> findByUser(User user);
}
