package com.doklad.api.dto;

public record NotificationDTO ( Long id, String title, String message, String type, String createdAt, String updatedAt) {
}
