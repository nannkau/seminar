package edu.sgu.seminar.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "invoice")
public class Invoice {
    @Id
    private String id;
    private Date createDate;
    private Integer priceTotal;
    private String code;
    @DBRef
    private User user;
    private String address;
    private String phoneNumber;
    @DBRef
    private List<InvoiceDetail> invoiceDetails;
}
