package edu.sgu.seminar.service.impl;

import edu.sgu.seminar.entity.Role;
import edu.sgu.seminar.repository.RoleRepository;
import edu.sgu.seminar.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(String id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void delete(Role role) {
         roleRepository.delete(role);
    }

    @Override
    public void deleteById(String id) {
        roleRepository.deleteById(id);
    }
}
