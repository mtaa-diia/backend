package com.doklad.api.customers.controllers;

import com.doklad.api.customers.dto.RoleDTO;
import com.doklad.api.customers.models.Role;
import com.doklad.api.customers.services.RoleService;
import com.doklad.api.customers.utility.exception.roleExceptions.RoleNotFoundException;
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
    public ResponseEntity<List<RoleDTO>> findAll() {
        List<Role> roles = roleService.findAll();
        List<RoleDTO> roleDTOS = roles.stream().map(this::convertToDto).collect(Collectors.toList());

        return ResponseEntity.ok(roleDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> findById(@PathVariable(name = "id") Long id) {
        Optional<Role> role = roleService.findById(id);

        if (role.isEmpty())
            throw new RoleNotFoundException("Role with id " + id.toString() + " was not found");

        return role.map(value -> ResponseEntity.ok(convertToDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> update(@PathVariable(name = "id") Long id) {
        Optional<Role> role = roleService.findById(id);

        if (role.isEmpty())
            throw new RoleNotFoundException("Role with id " + id.toString() + " was not found");

        Role updatedRole = role.get();
        RoleDTO updatedRoleDTO = convertToDto(roleService.update(updatedRole));

        return ResponseEntity.ok(updatedRoleDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") Long id) {
        Optional<Role> role = roleService.findById(id);

        if (role.isEmpty())
            throw new RoleNotFoundException("Role with id " + id.toString() + " was not found");

        roleService.deleteById(role.get().getId());

        return ResponseEntity.ok(HttpStatus.OK);
    }


    private RoleDTO convertToDto(Role role) {
        return modelMapper.map(role, RoleDTO.class);
    }

    private Role convertToEntity(RoleDTO roleDto) {
        return modelMapper.map(roleDto, Role.class);
    }
}
