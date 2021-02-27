package edu.sgu.seminar.repository;

import edu.sgu.seminar.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role,Integer> {
    Role findByCode(String code);
}
