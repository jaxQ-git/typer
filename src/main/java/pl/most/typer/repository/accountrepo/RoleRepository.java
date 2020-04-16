package pl.most.typer.repository.accountrepo;

import org.springframework.data.repository.CrudRepository;
import pl.most.typer.model.account.Role;
import pl.most.typer.model.account.RoleType;

public interface RoleRepository extends CrudRepository<Role,Long> {

    Role findByRoleType(RoleType roleType);
}
