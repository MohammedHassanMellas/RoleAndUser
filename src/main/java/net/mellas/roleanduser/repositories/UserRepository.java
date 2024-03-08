package net.mellas.roleanduser.repositories;

import net.mellas.roleanduser.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<User, String>{
    User findUserByUsername(String username);

}
