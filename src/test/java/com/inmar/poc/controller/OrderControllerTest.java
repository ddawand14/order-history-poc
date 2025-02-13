package com.inmar.poc.controller;


import com.inmar.poc.dto.ItemDto;
import com.inmar.poc.dto.OrderDto;
import com.inmar.poc.enums.Status;
import com.inmar.poc.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;



import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testGetOrderHistory() {
        List<ItemDto> listItemDto=new ArrayList<>();
        listItemDto.add(new ItemDto("product1","Laptop",40302.0,1));
        listItemDto.add(new ItemDto("product2","Mouse",2300.0,2));

        OrderDto order1 = new OrderDto(1L, new Date(), 100.0, listItemDto, Status.COMPLETED );
        OrderDto order2 = new OrderDto(2L, new Date(), 200.0, Collections.emptyList(), Status.PENDING );
        List<OrderDto> mockOrderHistory = Arrays.asList(order1, order2);

        when(orderService.getOrderHistory()).thenReturn(mockOrderHistory);

        ResponseEntity<List<OrderDto>> response = orderController.getOrderHistory();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockOrderHistory, response.getBody());
    }


}
