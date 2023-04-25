package com.doklad.api.developers.v1.services;

import com.doklad.api.customers.models.Order;
import com.doklad.api.customers.models.Status;
import com.doklad.api.customers.models.User;
import com.doklad.api.customers.repo.OrderRepository;
import com.doklad.api.customers.services.UserService;
import com.doklad.api.customers.utility.enums.StatusType;
import com.doklad.api.customers.utility.exception.userExceptions.UserNotFoundException;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
@Transactional(readOnly = true)
public class OrderDataService {

    private final OrderRepository orderRepository;
    private final UserDataService userDataService;
    private final Faker faker;

    @Autowired
    public OrderDataService(OrderRepository orderRepository, UserDataService userDataService, Faker faker) {
        this.orderRepository = orderRepository;
        this.userDataService = userDataService;
        this.faker = faker;
    }

    public List<Order> generateOrders(int count) {

        long randomUserId = faker.number().numberBetween(1, countOfUsers());
        Optional<User> user = userDataService.findById(randomUserId);
        List<Order> orders = new ArrayList<>();

        if (user.isEmpty())
            throw new UserNotFoundException("User with id: " + randomUserId + " not found");

        IntStream.range(0, count).forEach(i -> {
            String title = faker.lorem().sentence();
            String description = faker.lorem().paragraph();
            Order order = new Order(title, description, new Status(StatusType.PENDING));
            order.setUser(user.get());
            orders.add(order);
        });

        return orderRepository.findAll();
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Transactional
    public Order save(Order order) {
        order.setUpdatedAt(new Date());
        order.setCreatedAt(new Date());

        return orderRepository.save(order);
    }

    @Transactional
    public Order update(Order order) {
        order.setUpdatedAt(new Date());
        return orderRepository.save(order);
    }

    public long countOfUsers() {
        return userDataService.count();
    }
}
