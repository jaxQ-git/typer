package pl.most.typer.repository;

import org.springframework.data.repository.CrudRepository;
import pl.most.typer.model.role.Role;

public interface RoleRepository extends CrudRepository<Role,Long> {

    Role findByRoleType(String roleType);
}
