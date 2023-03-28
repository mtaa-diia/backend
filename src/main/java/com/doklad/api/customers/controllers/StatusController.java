package com.doklad.api.customers.controllers;

import com.doklad.api.customers.dto.StatusDTO;
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
    public ResponseEntity<List<StatusDTO>> findAll() {
        List<Status> statuses = statusService.findAll();
        List<StatusDTO> statusDTOS = statuses.stream().map(this::convertToDto).collect(Collectors.toList());

        return ResponseEntity.ok(statusDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusDTO> findById(@PathVariable(name = "id") Long id) {
        Optional<Status> status = statusService.findById(id);

        return status.map(value -> ResponseEntity.ok(convertToDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatusDTO> update(@PathVariable(name = "id") Long id, @RequestBody StatusDTO statusDto) {
        Optional<Status> status = statusService.findById(id);

        if (status.isEmpty())
            return ResponseEntity.notFound().build();

        Status updatedStatus = status.get();
        StatusDTO updatedStatusDTO =  convertToDto(statusService.update(updatedStatus));

        return ResponseEntity.ok(updatedStatusDTO);
    }


    private Status convertToEntity(StatusDTO statusDto) {
        return modelMapper.map(statusDto, Status.class);
    }

    private StatusDTO convertToDto(Status statuses) {
        return  modelMapper.map(statuses, StatusDTO.class);
    }
}
