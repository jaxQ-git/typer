package pl.most.typer.repository.accountrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.most.typer.model.account.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByToken(String token);

    Optional<User> findByUsernameOrMail(String username, String mail);

}
