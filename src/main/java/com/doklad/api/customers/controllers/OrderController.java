package com.doklad.api.customers.controllers;

import com.doklad.api.customers.dto.OrderDTO;
import com.doklad.api.customers.mappers.OrderMapper;
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
    private final OrderMapper orderMapper;

    @Autowired
    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<OrderDTO>> findAll() {
        List<Order> orders = orderService.findAll();
        List<OrderDTO> orderDTOs = orders.stream().map(this.orderMapper::convertToDto).collect(Collectors.toList());

        if (orders.isEmpty())
            throw new OrderNotFoundException("No orders were found");

        return ResponseEntity.ok(orderDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable(name = "id") Long id) {
        Optional<Order> order = orderService.findById(id);
        OrderDTO orderDTO = null;

        if (order.isEmpty())
            throw new OrderNotFoundException("Order with id " + id.toString() + " was not found");

        orderDTO = this.orderMapper.convertToDto(order.get());

        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }

    // create order unique number
    @PutMapping("/")
    public ResponseEntity<OrderDTO> update( @RequestBody OrderDTO orderDTO) {
        long orderDTOId = orderDTO.getId();
        Optional<Order> order = orderService.findById(orderDTOId);

        if (order.isEmpty())
            throw new OrderNotFoundException("Order with id " + orderDTOId + " was not found");

        Order orderEntity = this.orderMapper.convertToEntity(orderDTO);

        OrderDTO newOrderDTO = this.orderMapper.convertToDto(orderService.update(orderEntity));

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

}
