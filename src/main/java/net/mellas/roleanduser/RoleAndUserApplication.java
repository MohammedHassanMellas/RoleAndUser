package net.mellas.roleanduser;

import net.mellas.roleanduser.entities.Role;
import net.mellas.roleanduser.entities.User;
import net.mellas.roleanduser.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class RoleAndUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoleAndUserApplication.class, args);
    }
    @Bean
    CommandLineRunner start(UserService userService) {
        return args -> {
            User u = new User();
            u.setUsername("user1");
            u.setPassword("123456");
            userService.addNewUser(u);

            User u1 = new User();
            u1.setUsername("admin");
            u1.setPassword("654321");
            userService.addNewUser(u1);

            Stream.of("STUDENT", "USER", "ADMIN").
                    forEach(r -> {
                Role R = new Role();
                R.setRoleName(r);
                userService.addNewRole(R);
            });
            userService.addRoleToUser("user1","STUDENT");
            userService.addRoleToUser("user1","USER");
            userService.addRoleToUser("admin","ADMIN");
            userService.addRoleToUser("admin","USER");

            try {
                User user = userService.authenticate("user1","123456");
                System.out.println(user.getUserId());
                System.out.println(user.getUsername());
                System.out.println("Roles=>");
                user.getRoles().forEach(r->{
                    System.out.println("Roles=>"+r.toString());
                });
            }
            catch (Exception e){
                e.printStackTrace();
            }
        };
    }

}
