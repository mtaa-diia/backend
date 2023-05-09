package com.doklad.api.customers.dto;

public class SecretDTO {
    private String value;
    private Long documentId;
    private String expiresAt;

    public SecretDTO() {
    }

    public SecretDTO(String value, Long documentId, String expiresAt) {
        this.value = value;
        this.documentId = documentId;
        this.expiresAt = expiresAt;
    }

    public String getValue() {
        return value;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }
}
