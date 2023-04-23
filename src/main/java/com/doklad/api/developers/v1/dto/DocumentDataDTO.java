package com.doklad.api.developers.v1.dto;

import com.doklad.api.customers.utility.enums.StatusType;

import java.util.Objects;

public class DocumentDataDTO {
    private String content;
    private String description;
    private String title;
    private UserDataDTOId user;

    private StatusType status;

    public DocumentDataDTO() {
    }

    public DocumentDataDTO(String content, String description, String title, UserDataDTOId user, StatusType status) {
        this.content = content;
        this.description = description;
        this.title = title;
        this.user = user;
        this.status = status;
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

    public UserDataDTOId getUser() {
        return user;
    }

    public void setUser(UserDataDTOId user) {
        this.user = user;
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
        if (!(o instanceof DocumentDataDTO that)) return false;
        return Objects.equals(content, that.content) && Objects.equals(description, that.description) && Objects.equals(title, that.title) && Objects.equals(user, that.user) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, description, title, user, status);
    }

    @Override
    public String toString() {
        return "DocumentDataDTO{" +
                "content='" + content + '\'' +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", user=" + user.getId() +
                ", status=" + status +
                '}';
    }
}
