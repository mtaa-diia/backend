package com.doklad.api.customers.controllers;

import com.doklad.api.customers.models.Role;
import com.doklad.api.customers.services.UserService;
import com.doklad.api.customers.dto.UserDTO;
import com.doklad.api.customers.utility.enums.RoleType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();

//        List<UserDTO> userDTOs = users.stream().map(this::convertToDto).collect(Collectors.toList());

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable(name = "id") Long id) {
        Optional<User> user = userService.findById(id);

        if (user.isEmpty())
            return ResponseEntity.notFound().build();

        UserDTO userDTO = new UserDTO();
        userDTO.setRole(RoleType.USER);
        userDTO = convertToDto(user.get());

        return ResponseEntity.ok(userDTO);

    }

    @PostMapping("/")
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        User savedUser = userService.save(user);

        return ResponseEntity.ok(convertToDto(savedUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable(name = "id") Long id, @RequestBody UserDTO userDTO) {

        Optional<User> user = userService.findById(id);
        User updatedUser = convertToEntity(userDTO);

        if (user.isEmpty())
            return ResponseEntity.notFound().build();

        UserDTO updateUserDTO = convertToDto(userService.update(updatedUser));

        return ResponseEntity.ok(updateUserDTO);

    }

    // Write exception handler for delete method
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") Long id) {

        Optional<User> user = userService.findById(id);

        if (user.isEmpty())
            return ResponseEntity.notFound().build();

        userService.deleteById(id);

        return ResponseEntity.ok(HttpStatus.OK);

    }


    private UserDTO convertToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    private User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
