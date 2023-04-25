package com.doklad.api.customers.dto;

import com.doklad.api.customers.models.Status;
import com.doklad.api.customers.models.User;
import com.doklad.api.customers.utility.enums.StatusType;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

public class OrderDTO {

    private Long id;

    private String title;

    private String description;

    private Long userId;


    private StatusType status;

    private Long staffProcessedOrderId;


    private Date createdAt;

    private Date updatedAt;


    public OrderDTO() {
    }

    public OrderDTO(String title, String description, StatusType status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public Long getStaffProcessedOrderId() {
        return staffProcessedOrderId;
    }

    public void setStaffProcessedOrderId(Long staffProcessedOrderId) {
        this.staffProcessedOrderId = staffProcessedOrderId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDTO orderDTO)) return false;
        return Objects.equals(id, orderDTO.id) && Objects.equals(title, orderDTO.title) && Objects.equals(description, orderDTO.description) && Objects.equals(userId, orderDTO.userId) && status == orderDTO.status && Objects.equals(staffProcessedOrderId, orderDTO.staffProcessedOrderId) && Objects.equals(createdAt, orderDTO.createdAt) && Objects.equals(updatedAt, orderDTO.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, userId, status, staffProcessedOrderId, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", userId=" + userId +
                ", status=" + status +
                ", staffProcessedOrderId=" + staffProcessedOrderId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
