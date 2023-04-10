package com.doklad.api.customers.dto;

public class SecretDTO {
    private String value;
    private Long document;
    private String expiresAt;

    public SecretDTO() {

    }

    public SecretDTO(String value, Long document, String expiresAt) {
        this.value = value;
        this.document = document;
        this.expiresAt = expiresAt;
    }

    public String getValue() {
        return value;
    }

    public Long getDocument() {
        return document;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setDocument(Long document) {
        this.document = document;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }
}
