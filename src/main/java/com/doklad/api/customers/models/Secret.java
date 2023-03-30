package com.doklad.api.customers.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "secret")
public class Secret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "secret_id")
    private Long id;

    @Column(name = "value")
    @NotEmpty(message = "Secret value is required")
    @Size(min = 256, max = 256, message = "Secret value needs to be exactly 256 characters long")
    private String value;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "document_id")
    private Document document;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "expires_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiresAt;

    public Secret(Long id, String value, Document document, Date createdAt, Date expiresAt) {
        this.id = id;
        this.value = value;
        this.document = document;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }

    public Secret() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Secret secret = (Secret) o;
        return id.equals(secret.id) && value.equals(secret.value) && document.equals(secret.document) && createdAt.equals(secret.createdAt) && expiresAt.equals(secret.expiresAt);
    }

    @Override
    public String toString() {
        return "Secret{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", document=" + document +
                ", createdAt=" + createdAt +
                ", expiresAt=" + expiresAt +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, document, createdAt, expiresAt);
    }
}
