package com.doklad.api.customers.controllers;

import com.doklad.api.customers.dto.NotificationDTO;
import com.doklad.api.customers.mappers.NotificationMapper;
import com.doklad.api.customers.models.Notification;
import com.doklad.api.customers.services.NotificationService;
import com.doklad.api.customers.utility.exception.notificationExceptions.NotificationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final NotificationMapper notificationMapper;

    @Autowired
    public NotificationController(NotificationService notificationService, NotificationMapper notificationMapper) {
        this.notificationService = notificationService;
        this.notificationMapper = notificationMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<NotificationDTO>> findAll() {
        List<Notification> notifications = notificationService.findAll();
        List<NotificationDTO> notificationDTOs = notifications.stream().map(this.notificationMapper::convertToDto).collect(Collectors.toList());

        if (notifications.isEmpty())
            throw new NotificationNotFoundException("No notifications were found");

        return ResponseEntity.ok(notificationDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDTO> findById(@PathVariable(name = "id") Long id) {
        Optional<Notification> notification = notificationService.findById(id);

        if (notification.isEmpty())
            throw new NotificationNotFoundException("Notification with id " + id.toString() + " was not found");

        return notification.map(value -> ResponseEntity.ok(this.notificationMapper.convertToDto(value))).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PutMapping("/")
    public ResponseEntity<NotificationDTO> update(@RequestBody NotificationDTO notificationDTO) {

        Optional<Notification> notification = notificationService.findNotificationByUserId(notificationDTO.getNotificationId());
        Notification updatedNotification;
        NotificationDTO updatedNotificationDTO;

        if (notification.isEmpty())
            throw new NotificationNotFoundException("Notification with id " + notificationDTO.getNotificationId().toString() + " was not found");

        updatedNotification = notificationMapper.convertToEntity(notificationDTO);

        notificationService.update(updatedNotification);
        updatedNotificationDTO = notificationMapper.convertToDto(updatedNotification);

        return ResponseEntity.ok(updatedNotificationDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") Long id) {
        Optional<Notification> notification = notificationService.findById(id);

        if (notification.isEmpty())
            throw new NotificationNotFoundException("Notification with id " + id.toString() + " was not found");

        notificationService.deleteById(notification.get().getNotificationId());

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
