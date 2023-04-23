package com.doklad.api.developers.v1.dto;

import com.doklad.api.customers.utility.enums.RoleType;

public class UserDataDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private RoleType role;

}
