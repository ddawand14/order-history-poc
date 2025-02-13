package com.inmar.poc.dto;

import com.inmar.poc.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private Date orderDate;
    private double totalAmount;
    private List<ItemDto> items;
    private Status status;

}
