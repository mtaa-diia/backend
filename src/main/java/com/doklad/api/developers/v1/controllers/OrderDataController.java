package com.doklad.api.developers.v1.controllers;

import com.doklad.api.customers.dto.OrderDTO;
import com.doklad.api.customers.mappers.OrderMapper;
import com.doklad.api.customers.models.Order;
import com.doklad.api.developers.v1.services.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.doklad.api.developers.v1.services.OrderDataService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/dev/orders-data")
public class OrderDataController {


    private final OrderDataService orderDataService;
    private final OrderMapper orderMapper;
    private final UserDataService userDataService;

    @Autowired
    public OrderDataController(OrderDataService orderDataService, OrderMapper orderMapper, UserDataService userDataService) {
        this.orderDataService = orderDataService;
        this.orderMapper = orderMapper;
        this.userDataService = userDataService;
    }

    @GetMapping("/create")
    public ResponseEntity<List<OrderDTO>> createOrders(@RequestParam(name = "count", defaultValue = "1") int count) {
        List<Order> orders = orderDataService.generateOrders(count);
        List<OrderDTO> orderDTOS = new ArrayList<>();

        if (orders.isEmpty())
            return ResponseEntity.noContent().build();

        orders.forEach(this.orderDataService::save);
        orderDTOS = orders.stream().map(this.orderMapper::convertToDto).toList();

        return ResponseEntity.ok(orderDTOS);
    }

}
