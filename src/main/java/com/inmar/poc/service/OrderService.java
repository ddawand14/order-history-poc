package com.inmar.poc.service;

import com.inmar.poc.entity.Order;
import com.inmar.poc.dto.ItemDto;
import com.inmar.poc.dto.OrderDto;
import com.inmar.poc.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderDto> getOrderHistory(){
        List<Order> orders=orderRepository.findAll();
        return orders.stream()
                .map(order -> {
                    double totalAmount=order.getItems().stream()
                            .mapToDouble(item-> item.getQuantity() * item.getProduct().getPrice())
                            .sum();
                    List<ItemDto> itemDtos=order.getItems().stream()
                            .map(item -> new ItemDto(
                                    item.getProduct().getId(),
                                    item.getProduct().getProductName(),
                                    item.getProduct().getPrice(),
                                    item.getQuantity()))
                            .toList();
                    return new OrderDto(order.getId(), order.getOrderDate(),totalAmount,itemDtos,order.getStatus());
                }).collect(Collectors.toList());
    }
}
