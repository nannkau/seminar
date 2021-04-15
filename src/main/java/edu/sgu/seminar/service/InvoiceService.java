package edu.sgu.seminar.service;

import edu.sgu.seminar.dto.InvoiceDTO;
import edu.sgu.seminar.entity.Invoice;

import java.util.List;

public interface InvoiceService {
    Invoice addInvoice(InvoiceDTO invoiceDTO,String email);
    List<Invoice> getAll();
    Invoice findById(String id);
    List<Invoice> getInvoiceByEmail(String email);
}
