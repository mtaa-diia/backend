package com.doklad.api.developers.v1.services;

import com.doklad.api.customers.models.Notification;
import com.doklad.api.customers.models.User;
import com.doklad.api.customers.repo.NotificationRepository;
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
public class NotificationDataService {

    private final NotificationRepository notificationRepository;
    private final UserDataService userDataService;
    private final Faker faker;

    @Autowired
    public NotificationDataService(NotificationRepository notificationRepository, UserDataService userDataService, Faker faker) {
        this.notificationRepository = notificationRepository;
        this.userDataService = userDataService;
        this.faker = faker;
    }


    public List<Notification> generateNotifications(int count) {
        long randomUserId = faker.number().numberBetween(1, this.userDataService.count());
        Optional<User> user = userDataService.findById(randomUserId);
        List<Notification> notifications = new ArrayList<>();

        if (user.isEmpty())
            throw new UserNotFoundException("User with id: " + randomUserId + " not found");

        IntStream.range(0, count).forEach(i -> {
            String message = faker.lorem().paragraph();
            String title = faker.lorem().sentence();

            Notification notification = new Notification(title, message);
            notification.setUser(user.get());
            notification.setCreatedAt(new Date());
            notification.setUpdatedAt(new Date());
            notifications.add(notification);
        });


        return notifications;
    }

    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    public Optional<Notification> findById(Long id) {
        return notificationRepository.findById(id);
    }

    @Transactional
    public Notification save(Notification notification) {
        notification.setUpdatedAt(new Date());
        notification.setCreatedAt(new Date());
        return notificationRepository.save(notification);
    }

    @Transactional
    public Notification update(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Transactional
    public void deleteById(Long id) {
        notificationRepository.deleteById(id);
    }

    public long countOfNotifications() {
        return notificationRepository.count();
    }
}
