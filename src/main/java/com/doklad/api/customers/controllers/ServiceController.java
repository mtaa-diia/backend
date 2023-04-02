package com.doklad.api.customers.controllers;

import com.doklad.api.customers.dto.ServiceDto;
import com.doklad.api.customers.models.Service;
import com.doklad.api.customers.services.ServiceService;
import com.doklad.api.customers.utility.exception.serviceExceptions.ServiceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/service")
public class ServiceController {

    private final ServiceService serviceService;
    private final ModelMapper modelMapper;

    @Autowired
    public ServiceController(ServiceService serviceService, ModelMapper modelMapper) {
        this.serviceService = serviceService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/")
    public ResponseEntity<List<ServiceDto>> findAll() {
        List<Service> services = serviceService.findAll();
        List<ServiceDto> serviceDtos = services.stream().map(this::convertToDto).collect(Collectors.toList());

        return ResponseEntity.ok(serviceDtos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ServiceDto> findById(@PathVariable(name = "id") Long id) {
        Optional<Service> service = serviceService.findById(id);

        if (service.isEmpty())
            throw new ServiceNotFoundException("Service with id " + id.toString() + " was not found");

        return service.map(value -> ResponseEntity.ok(convertToDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceDto> update(@PathVariable(name = "id") Long id) {
        Optional<Service> service = serviceService.findById(id);

        if (service.isEmpty())
            throw new ServiceNotFoundException("Service with id " + id.toString() + " was not found");

        Service updatedService = service.get();
        ServiceDto updatedServiceDto = convertToDto(serviceService.update(updatedService));

        return ResponseEntity.ok(updatedServiceDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") Long id) {
        Optional<Service> service = serviceService.findById(id);

        if (service.isEmpty())
            throw new ServiceNotFoundException("Service with id " + id.toString() + " was not found");

        serviceService.deleteById(service.get().getId());

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private ServiceDto convertToDto(Service service) {
        return modelMapper.map(service, ServiceDto.class);
    }

    private Service convertToEntity(ServiceDto serviceDto) {
        return modelMapper.map(serviceDto, Service.class);
    }

}
