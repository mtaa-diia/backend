package com.doklad.api.models;
import com.doklad.api.utility.enums.StatusEnum;
import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Type;

import java.util.Date;


@Entity
@Table(name = "document")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    @NotEmpty(message = "Title is required")
    @Size(min = 3, max = 255, message = "Title must be between 3 and 50 characters")
    private String title;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Object content;

    @Column(name = "description")
    @NotEmpty(message = "Description is required")
    @Size(min = 3, max = 999, message = "Description must be between 3 and 999 characters")
    private String description;

    @Column(name = "user_id")
    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "status_id")
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


}
