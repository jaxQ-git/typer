package pl.most.typer.data;

import org.springframework.data.repository.CrudRepository;
import pl.most.typer.model.role.Role;

public interface RoleRepository extends CrudRepository<Role,Long> {

    Role findByRoleType(String roleType);
}
