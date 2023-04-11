package com.doklad.api.developers.v1.dto;

import java.util.Objects;

public class UserDataDTOId {
    private Long id;

    public UserDataDTOId() {
    }

    public UserDataDTOId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof UserDataDTOId that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UserDataDTOId{" +
                "id=" + id +
                '}';
    }
}
