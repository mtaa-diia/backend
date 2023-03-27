package com.doklad.api.developers.v1.controllers;

import com.doklad.api.customers.dto.UserDTO;
import com.doklad.api.customers.models.User;
import com.doklad.api.customers.services.UserService;
import com.doklad.api.developers.v1.services.UserDataService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/data/")
public class DataController {

    private final UserDataService userDataService;
    private UserService userService;

    @Autowired
    public DataController(UserDataService userDataService, UserService userService) {
        this.userDataService = userDataService;
        this.userService = userService;
    }

    @GetMapping("/create/user")
    public ResponseEntity<List<User>> createUser(@RequestParam(name = "count", defaultValue = "1") int count) {

        List<User> users = userDataService.generateUsers(count);

        // Save users to database
        users.forEach(userService::save);

        return new ResponseEntity<>(userDataService.generateUsers(count), HttpStatus.OK);
    }



}
