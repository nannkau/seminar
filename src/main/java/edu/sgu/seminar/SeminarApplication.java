package edu.sgu.seminar;

import edu.sgu.seminar.entity.Role;
import edu.sgu.seminar.entity.User;
import edu.sgu.seminar.repository.RoleRepository;
import edu.sgu.seminar.repository.UserRepository;
import edu.sgu.seminar.service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SeminarApplication {
    final private RoleRepository roleRepository;
    final private UserRepository userRepository;
    final private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    public SeminarApplication(RoleRepository roleRepository, UserRepository userRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SeminarApplication.class, args);
    }
    @Bean
    InitializingBean sendDatabase() {
        return () -> {
            if(roleRepository.findAll()==null||roleRepository.findAll().size()<1) {
                Role roleAd= new Role();
                roleAd.setCode("ADMIN");
                roleRepository.save(roleAd);
                Role roleCus= new Role();
                roleCus.setCode("CUSTOMER");
                roleRepository.save(roleCus);
            }
            if(userRepository.findAll()==null||userRepository.findAll().size()<1) {
                User user= new User();
                user.setEmail("admin@gmail.com");
                List<Role> roles= new ArrayList<>();
                roles.add(roleRepository.findByCode("ADMIN"));
                user.setRoles(roles);
                user.setActive("0");
                user.setPassword(passwordEncoder.encode("123456"));
                userRepository.save(user);
            }
        };
    }
}

