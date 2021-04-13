package edu.sgu.seminar.repository;

import edu.sgu.seminar.entity.InvoiceDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface InvoiceDetailRepository extends MongoRepository<InvoiceDetail,String> {
}
