package pl.most.typer.repository;

import org.springframework.data.repository.CrudRepository;
import pl.most.typer.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByToken(String token);
}
