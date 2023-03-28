package com.doklad.api.customers.dto;

import com.doklad.api.customers.utility.enums.StatusType;

public class DocumentDTO
{
    private String content;
    private String description;
    private String title;
    private Long user;

    private StatusType status;

    public DocumentDTO()
    {
    }

    public DocumentDTO(String content, String description, String title, Long user, StatusType status)
    {
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

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }
}
