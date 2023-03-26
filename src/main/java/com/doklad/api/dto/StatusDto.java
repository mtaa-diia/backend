package com.doklad.api.dto;

import com.doklad.api.utility.enums.StatusType;

public record StatusDto(Long id, StatusType status){
}
