package com.doklad.api.developers.v1.controllers;

import com.doklad.api.customers.dto.UserDTO;
import com.github.javafaker.Faker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/data/users")
public class DataController {

    @GetMapping("/create")
    public ResponseEntity<HttpStatus> generateUser(@RequestParam(name = "count", defaultValue = "1") int count) {

        return ResponseEntity.ok(HttpStatus.OK);
    }



}
