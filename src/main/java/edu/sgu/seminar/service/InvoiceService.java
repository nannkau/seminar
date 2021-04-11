package edu.sgu.seminar.service;

import edu.sgu.seminar.dto.InvoiceDTO;
import edu.sgu.seminar.entity.Invoice;

public interface InvoiceService {
    Invoice addInvoice(InvoiceDTO invoiceDTO,String email);
}
