package com.doklad.api.dto;

public record DocumentDTO( Long id, String name, String description, String type, String status, String createdAt, String updatedAt) {
}
