package com.doklad.api.customers.controllers;

import com.doklad.api.customers.dto.DocumentDTO;
import com.doklad.api.customers.dto.SecretDTO;
import com.doklad.api.customers.models.Document;
import com.doklad.api.customers.models.Secret;
import com.doklad.api.customers.services.DocumentService;
import com.doklad.api.customers.services.SecretService;
import com.doklad.api.customers.utility.exception.documentExceptions.DocumentNotFoundException;
import com.doklad.api.customers.utility.exception.secretExceptions.SecretNotFoundException;
import com.doklad.api.customers.utility.secrets.SecretValueGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/secrets")
public class SecretController {

    private final SecretService secretService;

    private final ModelMapper modelMapper;

    private final DocumentService documentService;

    @Autowired
    public SecretController(SecretService secretService, ModelMapper modelMapper, DocumentService documentService) {
        this.secretService = secretService;
        this.modelMapper = modelMapper;
        this.documentService = documentService;
    }

    @GetMapping("/{value}")
    public ResponseEntity<DocumentDTO> verify(@PathVariable(name = "value") String value) {
        Optional<Secret> secret = secretService.findByValue(value);

        if(secret.isEmpty())
            throw new SecretNotFoundException("Secret with the given value was not found");

        return ResponseEntity.ok(convertToDto(secret.get().getDocument()));
    }

    @PostMapping("/")
    public ResponseEntity<SecretDTO> generateSecret(@RequestBody Long documentId) {
        Optional<Document> document = documentService.findById(documentId);

        if (document.isEmpty())
            throw new DocumentNotFoundException("Document with id " + documentId.toString() + " was not found");

        Secret secret = new Secret();
        secret.setDocument(document.get());
        secret.setValue(SecretValueGenerator.generate());
        secret = secretService.save(secret);

        return ResponseEntity.ok(convertToDto(secret));
    }

    private DocumentDTO convertToDto(Document document) {
        return modelMapper.map(document, DocumentDTO.class);
    }

    private SecretDTO convertToDto(Secret secret) {
        return modelMapper.map(secret, SecretDTO.class);
    }
}
