package com.doklad.api.customers.dto;

public class DocumentDTO
{
    private Long id;
    private String content;
    private String description;
    private String title;
    private UserDTO user;

    private StatusDTO status;

    public DocumentDTO()
    {
    }

    public DocumentDTO(Long id, String content, String description, String title, UserDTO user, StatusDTO status)
    {
        this.id = id;
        this.content = content;
        this.description = description;
        this.title = title;
        this.user = user;
        this.status = status;
    }



    public Long getId()
    {
        return id;
    }
}
