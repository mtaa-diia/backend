package com.doklad.api.services;

import com.doklad.api.models.Order;
import com.doklad.api.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepo orderRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public List<Order> findAll() {
        return orderRepo.findAll();
    }

    public Optional<Order> findById(Long id) {
        return orderRepo.findById(id);
    }

    @Transactional
    public Order save(Order order) {
        order.setCreatedAt(new Date());
        order.setUpdatedAt(new Date());
        return orderRepo.save(order);
    }

    @Transactional
    public Order update(Order order) {
        order.setUpdatedAt(new Date());
        return orderRepo.save(order);
    }

    @Transactional
    public void deleteById(Long id) {
        orderRepo.deleteById(id);
    }

}
