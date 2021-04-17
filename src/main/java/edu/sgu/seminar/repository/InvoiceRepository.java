package edu.sgu.seminar.repository;

import edu.sgu.seminar.entity.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends MongoRepository<Invoice,String> {
    @Query(value="{ 'user.id' : ?0 }")
    List<Invoice> getInvoiceByUserId(String id);
    Invoice getInvoiceByCode(String code);
}
