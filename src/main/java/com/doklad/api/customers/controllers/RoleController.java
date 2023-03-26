package com.doklad.api.customers.controllers;

import com.doklad.api.customers.dto.RoleDto;
import com.doklad.api.customers.models.Role;
import com.doklad.api.customers.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleController(RoleService roleService, ModelMapper modelMapper) {
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<RoleDto>> findAll() {
        List<Role> roles = roleService.findAll();
        List<RoleDto> roleDtos = roles.stream().map(this::convertToDto).collect(Collectors.toList());

        return ResponseEntity.ok(roleDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> findById(@PathVariable(name = "id") Long id) {
        Optional<Role> role = roleService.findById(id);

        return role.map(value -> ResponseEntity.ok(convertToDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDto> update(@PathVariable(name = "id") Long id, @RequestBody RoleDto roleDto) {
        Optional<Role> role = roleService.findById(id);

        if (role.isEmpty())
            return ResponseEntity.notFound().build();

        Role updatedRole = role.get();
        RoleDto updatedRoleDto =  convertToDto(roleService.update(updatedRole));

        return ResponseEntity.ok(updatedRoleDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") Long id) {
        Optional<Role> role = roleService.findById(id);

        if (role.isEmpty())
            return ResponseEntity.notFound().build();

        roleService.deleteById(role.get().getId());

        return ResponseEntity.ok(HttpStatus.OK);
    }


    private RoleDto convertToDto(Role role) {
        return modelMapper.map(role, RoleDto.class);
    }

    private Role convertToEntity(RoleDto roleDto) {
        return modelMapper.map(roleDto, Role.class);
    }
}
