package com.doklad.api.customers.dto;

import com.doklad.api.customers.utility.enums.StatusType;

public record StatusDto(Long id, StatusType status){
}
