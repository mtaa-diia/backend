package com.doklad.api.customers.dto;

import com.doklad.api.customers.utility.enums.StatusType;

import java.util.Objects;

public class StatusDTO{
    private Long id;
    private StatusType statusType;


    public StatusDTO()
    {
    }

    public StatusDTO(Long id, StatusType statusType)
    {
        this.id = id;
        this.statusType = statusType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(StatusType statusType) {
        this.statusType = statusType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StatusDTO statusDTO)) return false;
        return Objects.equals(id, statusDTO.id) && statusType == statusDTO.statusType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, statusType);
    }

    @Override
    public String toString() {
        return "StatusDTO{" +
                "id=" + id +
                ", statusType=" + statusType +
                '}';
    }
}
