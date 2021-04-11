package edu.sgu.seminar.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "product")
public class Product {
    @Id
    private String id;
    private String gender;
    private String masterCategory;
    private String subCategory;
    private String baseColour;
    private String season;
    private String year;
    private String usage;
    private String productDisplayName;
    private Integer price;
}
