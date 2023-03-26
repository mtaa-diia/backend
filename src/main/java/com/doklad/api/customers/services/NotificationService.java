package com.doklad.api.customers.services;

import com.doklad.api.customers.models.Notification;
import com.doklad.api.customers.repo.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
@Transactional(readOnly = true)
public class NotificationService {

    private final NotificationRepo notificationRepo;

    @Autowired
    public NotificationService(NotificationRepo notificationRepo) {
        this.notificationRepo = notificationRepo;
    }

    public List<Notification> findAll() {
        return notificationRepo.findAll();
    }

    public Optional<Notification> findById(Long id) {
        return notificationRepo.findById(id);
    }

    @Transactional
    public Notification save(Notification notification) {
        notification.setCreatedAt(new Date());
        notification.setUpdatedAt(new Date());
        return notificationRepo.save(notification);
    }

    @Transactional
    public void update(Notification notification) {
        notification.setUpdatedAt(new Date());
        notificationRepo.save(notification);
    }

    @Transactional
    public void deleteById(Long id) {
        notificationRepo.deleteById(id);
    }
}
