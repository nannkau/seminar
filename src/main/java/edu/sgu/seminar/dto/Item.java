package edu.sgu.seminar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
    private Boolean selected;
    private String productId;
    private Integer amount;
}
