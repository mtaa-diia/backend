package com.doklad.api.customers.controllers;

import com.doklad.api.customers.dto.OrderDTO;
import com.doklad.api.customers.models.Order;
import com.doklad.api.customers.services.OrderService;
import com.doklad.api.customers.utility.exception.orderExceptions.OrderNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderController(OrderService orderService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<OrderDTO>> findAll() {
        List<Order> orders = orderService.findAll();
        List<OrderDTO> orderDTOs = orders.stream().map(this::convertToDto).collect(Collectors.toList());

        return ResponseEntity.ok(orderDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable(name = "id") Long id) {
        Optional<Order> order = orderService.findById(id);

        if (order.isEmpty())
            throw new OrderNotFoundException("Order with id " + id.toString() + " was not found");

        return order.map(value -> ResponseEntity.ok(convertToDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> update(@PathVariable(name = "id") Long id, @RequestBody OrderDTO orderDTO) {

        Optional<Order> order = orderService.findById(id);

        if (order.isEmpty())
            throw new OrderNotFoundException("Order with id " + id.toString() + " was not found");

        Order orderEntity = convertToEntity(orderDTO);

        OrderDTO newOrderDTO = convertToDto(orderService.update(orderEntity));

        return ResponseEntity.ok(newOrderDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") Long id) {
        Optional<Order> order = orderService.findById(id);

        if (order.isEmpty())
            throw new OrderNotFoundException("Order with id " + id.toString() + " was not found");

        orderService.deleteById(order.get().getId());

        return ResponseEntity.ok().build();
    }

    private OrderDTO convertToDto(Order order) {
        return modelMapper.map(order, OrderDTO.class);
    }

    private Order convertToEntity(OrderDTO orderDTO) {
        return modelMapper.map(orderDTO, Order.class);
    }
}
