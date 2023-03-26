package com.doklad.api.controllers;

import com.doklad.api.dto.OrderDTO;
import com.doklad.api.models.Order;
import com.doklad.api.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/orders")
public class OrderController{

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

        // Write exception handler for findById
        @GetMapping("/{id}")
        public ResponseEntity<OrderDTO> findById(@PathVariable(name = "id") Long id) {
            Optional<Order> order = orderService.findById(id);

            return order.map(value -> ResponseEntity.ok(convertToDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
        }

        // Write exception handler for update method
        @PutMapping("/{id}")
        public ResponseEntity<OrderDTO> update(@PathVariable(name = "id") Long id, @RequestBody OrderDTO orderDTO) {

            Optional<Order> order = orderService.findById(id);

            if(order.isEmpty())
                return ResponseEntity.notFound().build();

            Order orderEntity = convertToEntity(orderDTO);

            OrderDTO newOrderDTO = convertToDto(orderService.update(orderEntity));

            return ResponseEntity.ok(newOrderDTO);
        }

        // Write exception handler for delete method
        @DeleteMapping("/{id}")
        public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") Long id) {
            Optional<Order> order = orderService.findById(id);

            if(order.isEmpty())
                return ResponseEntity.notFound().build();

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
