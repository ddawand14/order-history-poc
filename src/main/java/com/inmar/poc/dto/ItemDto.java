package com.inmar.poc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemDto {

    private String productId;
    private  String productName;
    private double price;
    private int quantity;

}
