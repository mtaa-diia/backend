package com.doklad.api.customers.mappers;

import com.doklad.api.customers.dto.NotificationDTO;
import com.doklad.api.customers.models.Notification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public NotificationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public NotificationDTO convertToDto(Notification notification) {
        return modelMapper.map(notification, NotificationDTO.class);
    }



    public Notification convertToEntity(NotificationDTO notificationDTO) {
        Notification notification = modelMapper.map(notificationDTO, Notification.class);
        notification.setNotificationId(notificationDTO.getNotificationId());
        return notification;
    }

}
