package edu.sgu.seminar.service;

import edu.sgu.seminar.entity.Role;
import edu.sgu.seminar.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(String id);
    User findByEmail(String email);
    User save(User user);
    void delete(User user);
    void deleteById(String id);
}
