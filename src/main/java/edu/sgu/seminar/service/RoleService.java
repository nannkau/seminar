package edu.sgu.seminar.service;

import edu.sgu.seminar.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
    Role findById(String id);
    Role save(Role role);
    void delete(Role role);
    void deleteById(String id);
}
