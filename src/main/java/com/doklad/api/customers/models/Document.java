package com.doklad.api.customers.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "document")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private Long id;

    @Column(name = "title")
    @NotEmpty(message = "Title is required")
    @Size(min = 3, max = 255, message = "Title must be between 3 and 50 characters")
    private String title;

    @Column(columnDefinition = "jsonb")
    @NotEmpty(message = "Content is required")
    @Size(min = 3, max = 999, message = "Content must be between 3 and 999 characters")
    private String content;

    @Column(name = "description")
    @NotEmpty(message = "Description is required")
    @Size(min = 3, max = 999, message = "Description must be between 3 and 999 characters")
    private String description;

    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id")
    private Status status;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;


    public Document() {
    }

    public Document(String content, String description, String title, Status status, User user) {
        this.content = content;
        this.description = description;
        this.title = title;
        this.status = status;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
        if (!(o instanceof Document document)) return false;
        return Objects.equals(id, document.id) && Objects.equals(title, document.title) && Objects.equals(content, document.content) && Objects.equals(description, document.description) && Objects.equals(user, document.user) && Objects.equals(status, document.status) && Objects.equals(createdAt, document.createdAt) && Objects.equals(updatedAt, document.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, description, user, status, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
