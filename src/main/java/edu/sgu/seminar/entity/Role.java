package edu.sgu.seminar.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collation = "role")
public class Role {
    private Integer id;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
    private String code;
}
