package com.doklad.api.customers.dto;

public record DocumentDTO( Long id, String name, String description, String type, String status, String createdAt, String updatedAt) {
}
