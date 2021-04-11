package edu.sgu.seminar.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document(collection = "invoice_detail")
public class InvoiceDetail {
    @Id
    private String id;
    @DBRef
    private Product product;
    private Integer total;
}
