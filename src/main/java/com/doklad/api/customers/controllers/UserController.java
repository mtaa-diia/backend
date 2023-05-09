package com.doklad.api.customers.controllers;

import com.doklad.api.customers.dto.DocumentDTO;
import com.doklad.api.customers.dto.UserDTO;
import com.doklad.api.customers.mappers.DocumentMapper;
import com.doklad.api.customers.mappers.UserMapper;
import com.doklad.api.customers.models.Document;
import com.doklad.api.customers.models.User;
import com.doklad.api.customers.services.DocumentService;
import com.doklad.api.customers.services.UserService;
import com.doklad.api.customers.utility.enums.RoleType;
import com.doklad.api.customers.utility.exception.documentExceptions.DocumentNotFoundException;
import com.doklad.api.customers.utility.exception.userExceptions.UserAlreadyExistException;
import com.doklad.api.customers.utility.exception.userExceptions.UserNotFoundException;
import com.doklad.api.customers.utility.exception.userExceptions.UsersNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;


@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private DocumentMapper documentMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper, DocumentService documentService, DocumentMapper documentMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.documentMapper = documentMapper;
    }


    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> users = userService.findAll();

        if (users.isEmpty())
            throw new UsersNotFoundException("No users were found");

        List<UserDTO> userDTOs = users.stream().map(this.userMapper::convertUserToUserDTO).toList();

        return ResponseEntity.ok(userDTOs);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable(name = "id") Long id) {
        Optional<User> user = userService.findById(id);

        if (user.isEmpty())
            throw new UserNotFoundException("User with id " + id.toString() + " was not found");

        UserDTO userDTO = new UserDTO();
        userDTO.setRole(RoleType.USER);
        userDTO = userMapper.convertUserToUserDTO(user.get());

        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> findByUsername(@PathVariable(name = "username") String username) {
        Optional<User> user = userService.findByUsername(username);

        if (user.isEmpty())
            throw new UserNotFoundException("User with username " + username + " was not found");

        UserDTO userDTO = new UserDTO();
        userDTO.setRole(RoleType.USER);
        userDTO = userMapper.convertUserToUserDTO(user.get());

        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/")
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        Optional<User> userOptional = userService.findByUsername(userDTO.getUsername());
        User user = null;
        User savedUser = null;

        if (userOptional.isPresent())
            throw new UserAlreadyExistException("User with username " + userDTO.getUsername() + " already exists");


        user = userMapper.convertUserDTOToUser(userDTO);
        userService.save(user);


        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable(name = "id") Long id, @RequestBody UserDTO userDTO) {

        Optional<User> user = userService.findById(id);
        User updatedUser = userMapper.convertUserDTOToUser(userDTO);

        if (user.isEmpty())
            throw new UserNotFoundException("User with id " + id.toString() + " was not found");

        UserDTO updateUserDTO = userMapper.convertUserToUserDTO(userService.update(updatedUser));

        return ResponseEntity.ok(updateUserDTO);

    }

    @GetMapping("/documents/")
    public List<DocumentDTO> findAllUserDocuments() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> authUser = this.userService.findByUsername(username);
        if (authUser.isEmpty())
            throw new UserNotFoundException("User with username " + username + " was not found");

        List<Document> documents = userService.findAllDocumentsByUserId(authUser.get().getId());

        if (documents.isEmpty())
            throw new DocumentNotFoundException("No documents were found");


        return documents.stream().map(this.documentMapper::convertToDto).toList();
    }

    @PreAuthorize("hasRole('STAFF')")
    @GetMapping("/documents/{userId}")
    public List<DocumentDTO> findAllUserDocuments(@PathVariable(name = "userId") Long userId) {
        Optional<User> user = this.userService.findById(userId);
        if (user.isEmpty())
            throw new UserNotFoundException("User with id " + userId + " was not found");

        List<Document> documents = userService.findAllDocumentsByUserId(user.get().getId());

        if (documents.isEmpty())
            throw new DocumentNotFoundException("No documents were found");

        return documents.stream().map(this.documentMapper::convertToDto).toList();
    }



    // Write exception handler for delete method
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteById(@PathVariable(name = "id") Long id) {

        Optional<User> user = userService.findById(id);

        if (user.isEmpty())
            throw new UserNotFoundException("User with id " + id.toString() + " was not found");


        userService.deleteById(id);

        return ResponseEntity.ok(user.get());

    }

    @DeleteMapping("/")
    public ResponseEntity<String> deleteByUsername(@RequestParam(name = "username") String username) {
        Optional<User> user = userService.findByUsername(username);

        if (user.isEmpty())
            throw new UserNotFoundException("User with username " + username + " was not found");

        userService.deleteById(user.get().getId());

        return ResponseEntity.ok("User with username " + username + " was deleted");
    }


}
