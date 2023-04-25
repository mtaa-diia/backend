package com.doklad.api.customers.dto;

import com.doklad.api.customers.models.Notification;
import com.doklad.api.customers.models.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

public class NotificationDTO {

    private Long notificationId;

    private String title;

    private String message;

    private Date createdAt;


    private Date updatedAt;

    private Date readAt;

    private Long userId;


    public NotificationDTO() {
    }

    public NotificationDTO(String title, String message) {
        this.title = title;
        this.message = message;

    }

    public Long getNotificationId() {
        return notificationId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public Date getReadAt() {
        return readAt;
    }

    public void setReadAt(Date readAt) {
        this.readAt = readAt;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotificationDTO that)) return false;
        return Objects.equals(notificationId, that.notificationId) && Objects.equals(title, that.title) && Objects.equals(message, that.message) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(readAt, that.readAt) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notificationId, title, message, createdAt, updatedAt, readAt, userId);
    }

    @Override
    public String toString() {
        return "NotificationDTO{" +
                "notificationId=" + notificationId +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", readAt=" + readAt +
                ", userId=" + userId +
                '}';
    }
}
