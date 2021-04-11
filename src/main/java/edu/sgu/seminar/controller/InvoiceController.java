package edu.sgu.seminar.controller;

import edu.sgu.seminar.dto.InvoiceDTO;
import edu.sgu.seminar.dto.Item;
import edu.sgu.seminar.entity.Invoice;
import edu.sgu.seminar.entity.Product;
import edu.sgu.seminar.service.InvoiceService;
import edu.sgu.seminar.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class InvoiceController {
    @Autowired
    private ProductService productService;
    @Autowired
    private InvoiceService invoiceService;
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
                count++;
            }
        }
        if (result.hasErrors()|| count==0) {
            List<Product> products= productService.getAll();
            model.addAttribute("products",products);
            return "cart";
        }
        else {
            Invoice invoice= invoiceService.addInvoice(invoiceDTO,authentication.getName());

            return "";
        }

    }
}
