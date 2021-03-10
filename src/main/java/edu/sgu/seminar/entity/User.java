package edu.sgu.seminar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.List;

@Data
@Document(collection = "user")
public class User implements Serializable {
    @Id
    private String id;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
    @Email( message = "Email should be valid")
    private String email;
    private String password;
    private String fullName;
    private String active;
    @DBRef
    private List<Role> roles;
}
