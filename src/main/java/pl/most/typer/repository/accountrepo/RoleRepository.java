package pl.most.typer.repository.accountrepo;

import org.springframework.data.repository.CrudRepository;
import pl.most.typer.model.role.Role;
import pl.most.typer.model.role.RoleType;

public interface RoleRepository extends CrudRepository<Role,Long> {

    Role findByRoleType(RoleType roleType);
}
