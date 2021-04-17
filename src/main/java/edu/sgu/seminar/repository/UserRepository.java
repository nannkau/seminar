package edu.sgu.seminar.repository;

import edu.sgu.seminar.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    User findByEmail(String email);
    User getUserByCode(String code);
}
