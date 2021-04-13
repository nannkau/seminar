package edu.sgu.seminar.service.impl;

import edu.sgu.seminar.dto.InvoiceDTO;
import edu.sgu.seminar.dto.Item;
import edu.sgu.seminar.entity.*;
import edu.sgu.seminar.repository.InvoiceDetailRepository;
import edu.sgu.seminar.repository.InvoiceRepository;
import edu.sgu.seminar.repository.ProductRepository;
import edu.sgu.seminar.repository.UserRepository;
import edu.sgu.seminar.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;
    @Override
    public Invoice addInvoice(InvoiceDTO invoiceDTO, String email) {
        User user=userRepository.findByEmail(email);
        Invoice invoice = new Invoice();
        invoice.setCreateDate(new Date());
        invoice.setUser(user);
        invoice.setAddress(invoiceDTO.getAddress());
        invoice.setPhoneNumber(invoiceDTO.getPhoneNumber());
        List<InvoiceDetail> invoiceDetails= new ArrayList<>();
        Integer price=0;
        for (Item item: invoiceDTO.getList()){
            if(item.getSelected()==true){
                InvoiceDetail invoiceDetail= new InvoiceDetail();
                Product product= productRepository.findById(item.getProductId()).get();
                price=price+(product.getPrice()*item.getAmount());
                invoiceDetail.setProduct(product);
                invoiceDetail.setTotal(item.getAmount());
                invoiceDetails.add(invoiceDetailRepository.save(invoiceDetail));
            }
        }
        invoice.setPriceTotal(price);
        invoice.setInvoiceDetails(invoiceDetails);
        return invoiceRepository.save(invoice);
    }

    @Override
    public List<Invoice> getAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice findById(String id) {
        return invoiceRepository.findById(id).get();
    }
}
