package com.doklad.api.customers.controllers;

import com.doklad.api.customers.services.UserService;
import com.doklad.api.customers.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.doklad.api.customers.models.User;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;


    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> users = userService.findAll();
        List<UserDTO> userDTOs = users.stream().map(this::convertToDto).collect(Collectors.toList());

        return ResponseEntity.ok(userDTOs);
    }

    // Write exception handler for findById
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable(name = "id") Long id) {
        Optional<User> user = userService.findById(id);

        return user.map(value -> ResponseEntity.ok(convertToDto(value))).orElseGet(() -> ResponseEntity.notFound().build());

    }

    // Write exception handler for update method
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable(name = "id") Long id, @RequestBody UserDTO userDTO) {

        Optional<User> user = userService.findById(id);

        return user.map(value -> ResponseEntity.ok(convertToDto(value))).orElseGet(() -> ResponseEntity.notFound().build());

    }

    // Write exception handler for delete method
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable(name = "id") Long id) {

        Optional<User> user = userService.findById(id);

        return user.map(value -> ResponseEntity.ok(convertToDto(value))).orElseGet(() -> ResponseEntity.notFound().build());

    }


    private UserDTO convertToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    private User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
