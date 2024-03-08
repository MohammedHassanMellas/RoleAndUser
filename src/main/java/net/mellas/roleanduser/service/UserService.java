package net.mellas.roleanduser.service;

import net.mellas.roleanduser.entities.Role;
import net.mellas.roleanduser.entities.User;

public interface UserService {
    User addNewUser(User user);
    Role addNewRole(Role role);
    User findUserByUserName(String userName);
    Role findRoleByRoleName(String roleName);

    void addRoleToUser(String userName, String roleName);
    User authenticate(String username,String password);


}

