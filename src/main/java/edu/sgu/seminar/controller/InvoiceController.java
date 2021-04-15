package edu.sgu.seminar.controller;

import edu.sgu.seminar.dto.InvoiceDTO;
import edu.sgu.seminar.dto.Item;
import edu.sgu.seminar.dto.QrCodeProcessingResult;
import edu.sgu.seminar.entity.Invoice;
import edu.sgu.seminar.entity.Product;
import edu.sgu.seminar.repository.InvoiceRepository;
import edu.sgu.seminar.service.InvoiceService;
import edu.sgu.seminar.service.ProductService;
import edu.sgu.seminar.service.QrCodeEncoderService;
import edu.sgu.seminar.utils.ProductToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class InvoiceController {
    @Autowired
    private ProductService productService;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private QrCodeEncoderService qrCodeEncoderService;
    @RequestMapping(value = "/invoice/list.html")
    public String list(Model model,Authentication authentication){
        if (authentication!=null){
            List<Invoice> invoices=invoiceService.getInvoiceByEmail(authentication.getName());
            model.addAttribute("list",invoices);
            return "invoice";
        }
        return "redirect:/login";
    }
    @RequestMapping(value = "/invoice/add.html")
    public String add(Model model){
        List<Product> products= productService.getAll();
        model.addAttribute("products",products);
        List<Item> items= new ArrayList<>();
        for (Product product: products){
            Item item= new Item();
            item.setProductId(product.getId());
            item.setSelected(false);
            items.add(item);

        }
        InvoiceDTO invoiceDTO= new InvoiceDTO();
        invoiceDTO.setList(items);
        model.addAttribute("invoiceDTO",invoiceDTO);
        return "cart";
    }
    @RequestMapping(value = "/invoice/add.html",method = RequestMethod.POST)
    public String add(Model model, @Valid InvoiceDTO invoiceDTO, Authentication authentication, BindingResult result){
        Integer count=0;
        for(Item item: invoiceDTO.getList()){
            if (item.getSelected()==true){
                if (item.getAmount()==null)
                {
                    count=-1;
                }
                count++;
            }
        }
        if (result.hasErrors()|| count<1) {
            List<Product> products= productService.getAll();
            model.addAttribute("products",products);
            return "cart";
        }
        else {
            Invoice invoice= invoiceService.addInvoice(invoiceDTO,authentication.getName());

            return "redirect:/invoice/list.html";
        }

    }
    @RequestMapping("invoice/detail.html/{id}")
    public String detail(Model model, @PathVariable("id") String id){
        Invoice invoice =invoiceService.findById(id);
        String payload= ProductToString.productToStringEmv(invoice);
        QrCodeProcessingResult result= qrCodeEncoderService.generateImageAsBase64(payload);
        model.addAttribute("invoice",invoice);
        model.addAttribute("result",result);
        return "detail";
    }
}
