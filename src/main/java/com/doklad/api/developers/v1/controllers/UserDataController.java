package com.doklad.api.developers.v1.controllers;

import com.doklad.api.customers.models.User;
import com.doklad.api.customers.services.UserService;
import com.doklad.api.developers.v1.services.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dev/users-data/")
public class UserDataController {

    private final UserDataService userDataService;
    private UserService userService;

    @Autowired
    public UserDataController(UserDataService userDataService, UserService userService) {
        this.userDataService = userDataService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public ResponseEntity<List<User>> createUser(@RequestParam(name = "count", defaultValue = "1") int count) {

        List<User> users = userDataService.generateUsers(count);

        // Save users to database
        users.forEach(userDataService::save);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }


}
