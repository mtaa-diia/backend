package com.doklad.api.customers.services;

import com.doklad.api.customers.models.Notification;
import com.doklad.api.customers.repo.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
@Transactional(readOnly = true)
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    public Optional<Notification> findById(Long id) {
        return notificationRepository.findById(id);
    }

    public Optional<Notification> findNotificationByUserId(Long id) {
        return notificationRepository.findNotificationByUserId(id);
    }

    @Transactional
    public Notification save(Notification notification) {
        notification.setCreatedAt(new Date());
        notification.setUpdatedAt(new Date());
        return notificationRepository.save(notification);
    }

    @Transactional
    public Notification update(Notification notification) {
        notification.setUpdatedAt(new Date());
        return notificationRepository.save(notification);
    }

    @Transactional
    public void deleteById(Long id) {
        notificationRepository.deleteById(id);
    }
}
