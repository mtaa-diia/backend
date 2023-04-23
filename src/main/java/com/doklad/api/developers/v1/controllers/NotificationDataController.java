package com.doklad.api.developers.v1.controllers;

import com.doklad.api.customers.dto.NotificationDTO;
import com.doklad.api.customers.mappers.NotificationMapper;
import com.doklad.api.customers.models.Notification;
import com.doklad.api.developers.v1.services.NotificationDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/dev/notifications-data")
public class NotificationDataController {

    private final NotificationDataService notificationDataService;
    private final NotificationMapper notificationMapper;

    @Autowired
    public NotificationDataController(NotificationDataService notificationDataService, NotificationMapper notificationMapper) {
        this.notificationDataService = notificationDataService;
        this.notificationMapper = notificationMapper;
    }

    @GetMapping("/create")
    public ResponseEntity<List<NotificationDTO>> createNotification(@RequestParam(name = "count", defaultValue = "1") int count) {
        List<Notification> notifications = notificationDataService.generateNotifications(count);
        List<NotificationDTO> notificationDTOs = notifications.stream().map(this.notificationMapper::convertToDto).toList();
        notifications.forEach(this.notificationDataService::save);

        return ResponseEntity.ok(notificationDTOs);

    }
}
