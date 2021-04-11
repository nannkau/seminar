package edu.sgu.seminar.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class InvoiceDTO {
    List<Item> list;
    private String address;
    private String phoneNumber;
}
