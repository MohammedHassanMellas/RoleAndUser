package net.mellas.roleanduser.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import net.mellas.roleanduser.entities.Role;
import net.mellas.roleanduser.entities.User;
import net.mellas.roleanduser.repositories.RoleRepository;
import net.mellas.roleanduser.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Override
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }
    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }
    @Override
    public User findUserByUserName(String username) {
        return userRepository.findUserByUsername(username);
    }
    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepository.findRoleByRoleName(roleName);
    }
    @Override
    public void addRoleToUser(String username, String roleName) {
         User user = findUserByUserName(username);
         Role role = findRoleByRoleName(roleName);
         if (user.getRoles() != null){
             user.getRoles().add(role);
             role.getUsers().add(user);
         }
        // userRepository.save(user);
    }

    @Override
    public User authenticate(String username, String password) {
        User user=userRepository.findUserByUsername(username);
        if (user==null) throw new RuntimeException("Bad credentials");
        if(user.getPassword().equals(password)){
            return user;
        }
        throw new RuntimeException("Bad credentials");
    }
}
