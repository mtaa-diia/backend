package com.doklad.api.developers.v1.controllers;

import com.doklad.api.customers.dto.UserDTO;
import com.doklad.api.customers.models.User;
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

    @Autowired
    public DataController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @GetMapping("/create/user")
    public ResponseEntity<List<User>> createUser(@RequestParam(name = "count", defaultValue = "1") int count) {

        return new ResponseEntity<>(userDataService.generateUsers(count), HttpStatus.OK);
    }



}
