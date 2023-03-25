package com.doklad.api.controllers;

import com.doklad.api.dto.NotificationDTO;
import com.doklad.api.models.Notification;
import com.doklad.api.services.NotificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final ModelMapper modelMapper;

    @Autowired
    public NotificationController(NotificationService notificationService, ModelMapper modelMapper) {
        this.notificationService = notificationService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<NotificationDTO>> findAll() {
        List<Notification> notifications = notificationService.findAll();
        List<NotificationDTO> notificationDTOs = notifications.stream().map(this::convertToDto).collect(Collectors.toList());

        return ResponseEntity.ok(notificationDTOs);
    }

    // Write exception handler for findById
    @GetMapping("/{id}")
    public ResponseEntity<NotificationDTO> findById(@PathVariable(name = "id") Long id) {
        Optional<Notification> notification = notificationService.findById(id);

        return notification.map(value -> ResponseEntity.ok(convertToDto(value))).orElseGet(() -> ResponseEntity.notFound().build());

    }

    // Write exception handler for update method
    @PutMapping("/{id}")
    public ResponseEntity<NotificationDTO> update(@PathVariable(name = "id") Long id, @RequestBody NotificationDTO notificationDTO) {

        Optional<Notification> notification = notificationService.findById(id);

        return notification.map(value -> ResponseEntity.ok(convertToDto(value))).orElseGet(() -> ResponseEntity.notFound().build());

    }

    // Write exception handler for delete method
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") Long id) {
        Optional<Notification> notification = notificationService.findById(id);

        if (notification.isEmpty())
            return ResponseEntity.notFound().build();

        notificationService.deleteById(notification.get().getId());

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private NotificationDTO convertToDto(Notification notification) {
        return modelMapper.map(notification, NotificationDTO.class);
    }

    private Notification convertToEntity(NotificationDTO notificationDTO) {
        return modelMapper.map(notificationDTO, Notification.class);
    }
}
