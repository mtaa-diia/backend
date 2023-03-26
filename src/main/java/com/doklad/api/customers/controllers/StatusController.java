package com.doklad.api.customers.controllers;

import com.doklad.api.customers.dto.StatusDto;
import com.doklad.api.customers.models.Status;
import com.doklad.api.customers.services.StatusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;


@RestController
@RequestMapping("/api/status")
public class StatusController {

    private final StatusService statusService;
    private final ModelMapper modelMapper;

    @Autowired
    public StatusController(StatusService statusService, ModelMapper modelMapper) {
        this.statusService = statusService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<StatusDto>> findAll() {
        List<Status> statuses = statusService.findAll();
        List<StatusDto> statusDtos = statuses.stream().map(this::convertToDto).collect(Collectors.toList());

        return ResponseEntity.ok(statusDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusDto> findById(@PathVariable(name = "id") Long id) {
        Optional<Status> status = statusService.findById(id);

        return status.map(value -> ResponseEntity.ok(convertToDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatusDto> update(@PathVariable(name = "id") Long id, @RequestBody StatusDto statusDto) {
        Optional<Status> status = statusService.findById(id);

        if (status.isEmpty())
            return ResponseEntity.notFound().build();

        Status updatedStatus = status.get();
        StatusDto updatedStatusDto =  convertToDto(statusService.update(updatedStatus));

        return ResponseEntity.ok(updatedStatusDto);
    }


    private Status convertToEntity(StatusDto statusDto) {
        return modelMapper.map(statusDto, Status.class);
    }

    private StatusDto convertToDto(Status statuses) {
        return  modelMapper.map(statuses, StatusDto.class);
    }
}
