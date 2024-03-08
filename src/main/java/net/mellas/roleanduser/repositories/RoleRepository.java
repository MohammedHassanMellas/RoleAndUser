package net.mellas.roleanduser.repositories;

import net.mellas.roleanduser.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface RoleRepository extends JpaRepository<Role, Long>{
      Role findRoleByRoleName(String roleName);

}
