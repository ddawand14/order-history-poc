package com.inmar.poc.service;


import com.inmar.poc.dto.OrderDto;
import com.inmar.poc.entity.Item;
import com.inmar.poc.entity.Order;
import com.inmar.poc.entity.Product;
import com.inmar.poc.enums.Status;
import com.inmar.poc.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    private Order order1;
    private Order order2;

    @BeforeEach
    public void setUp() {
        Product product1 = new Product("101", "Product A", 50.0); // Use String for id
        Product product2 = new Product("102", "Product B", 30.0); // Use String for id

        order1 = new Order(1L, new Date(), Status.COMPLETED, null); // Use Status enum
        order2 = new Order(2L, new Date(), Status.PENDING, null); // Use Status enum

        Item item1 = new Item(1L, order1, product1, 2); // Pass order and product
        Item item2 = new Item(2L, order2, product2, 3); // Pass order and product

        order1.setItems(Arrays.asList(item1));
        order2.setItems(Arrays.asList(item2));

    }

    @Test
    public void testGetOrderHistory() {
        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));

        List<OrderDto> orderHistory = orderService.getOrderHistory();

        assertEquals(2, orderHistory.size());

        OrderDto orderDto1 = orderHistory.get(0);
        assertEquals(1L, orderDto1.getId());
        assertEquals(100.0, orderDto1.getTotalAmount()); // 2 * 50.0 = 100.0
        assertEquals("COMPLETED", orderDto1.getStatus().toString());
        assertEquals(1, orderDto1.getItems().size());
        assertEquals("101", orderDto1.getItems().get(0).getProductId());
        assertEquals("Product A", orderDto1.getItems().get(0).getProductName());
        assertEquals(50.0, orderDto1.getItems().get(0).getPrice());
        assertEquals(2, orderDto1.getItems().get(0).getQuantity());

        OrderDto orderDto2 = orderHistory.get(1);
        assertEquals(2L, orderDto2.getId());
        assertEquals(90.0, orderDto2.getTotalAmount());
        assertEquals("PENDING", orderDto2.getStatus().toString());
        assertEquals(1, orderDto2.getItems().size());
        assertEquals("102", orderDto2.getItems().get(0).getProductId());
        assertEquals("Product B", orderDto2.getItems().get(0).getProductName());
        assertEquals(30.0, orderDto2.getItems().get(0).getPrice());
        assertEquals(3, orderDto2.getItems().get(0).getQuantity());
    }
}
