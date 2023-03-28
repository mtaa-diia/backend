package com.doklad.api.customers.dto;

import com.doklad.api.customers.utility.enums.RoleType;

import java.util.Objects;

public class RoleDto {
    private Long id;
    private RoleType role;

    public RoleDto() {
    }

    public RoleDto(Long id, RoleType role) {
        this.id = id;
        this.role = role;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof RoleDto roleDto)) return false;
        return Objects.equals(id, roleDto.id) && role == roleDto.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role);
    }

    @Override
    public String toString() {
        return "RoleDto{" +
                "id=" + id +
                ", role=" + role +
                '}';
    }
}
