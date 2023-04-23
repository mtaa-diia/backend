package com.doklad.api.customers.dto;

import com.doklad.api.customers.utility.enums.StatusType;

import java.util.Objects;

public class DocumentDTO {

    private Long id;
    private String content;
    private String description;
    private String title;
    private Long userId;

    private StatusType status;

    public DocumentDTO() {
    }

    public DocumentDTO(Long id, String content, String description, String title, Long userId, StatusType status) {
        this.id = id;
        this.content = content;
        this.description = description;
        this.title = title;
        this.userId = userId;
        this.status = status;
    }

    public DocumentDTO(String content, String description, String title, Long userId, StatusType status) {
        this.content = content;
        this.description = description;
        this.title = title;
        this.userId = userId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DocumentDTO that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(content, that.content) && Objects.equals(description, that.description) && Objects.equals(title, that.title) && Objects.equals(userId, that.userId) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, description, title, userId, status);
    }

    @Override
    public String toString() {
        return "DocumentDTO{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", user=" + userId +
                ", status=" + status +
                '}';
    }
}
