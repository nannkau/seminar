package edu.sgu.seminar.service.impl;

import edu.sgu.seminar.entity.Role;
import edu.sgu.seminar.entity.User;
import edu.sgu.seminar.repository.RoleRepository;
import edu.sgu.seminar.repository.UserRepository;
import edu.sgu.seminar.service.UserService;
import edu.sgu.seminar.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    final private UserRepository userRepository;
    final private RoleRepository roleRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<Role> roles= new ArrayList<>();
        roles.add(roleRepository.findByCode("CUSTOMER"));
        String code= RandomUtils.randomCode(10000000,99999999);
        while (userRepository.getUserByCode(code)!=null){
            code= RandomUtils.randomCode(10000000,99999999);
        }
        user.setCode(code);
        user.setRoles(roles);
        user.setActive("0");
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}
