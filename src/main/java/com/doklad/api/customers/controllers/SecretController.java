package com.doklad.api.customers.controllers;

import com.doklad.api.customers.dto.DocumentDTO;
import com.doklad.api.customers.models.Document;
import com.doklad.api.customers.models.Secret;
import com.doklad.api.customers.services.SecretService;
import com.doklad.api.customers.utility.exception.secretExceptions.SecretNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/secrets")
public class SecretController {

    private final SecretService secretService;

    private final ModelMapper modelMapper;

    @Autowired
    public SecretController(SecretService secretService, ModelMapper modelMapper) {
        this.secretService = secretService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{value}")
    public ResponseEntity<DocumentDTO> verify(@PathVariable(name = "value") String value) {
        Optional<Secret> secret = secretService.findByValue(value);

        if(secret.isEmpty())
            throw new SecretNotFoundException("Secret with the given value was not found");

        return ResponseEntity.ok(convertToDto(secret.get().getDocument()));
    }

    private DocumentDTO convertToDto(Document document) {
        return modelMapper.map(document, DocumentDTO.class);
    }
}
