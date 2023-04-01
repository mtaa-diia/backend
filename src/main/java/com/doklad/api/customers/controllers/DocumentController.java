package com.doklad.api.customers.controllers;

import com.doklad.api.customers.dto.DocumentDTO;
import com.doklad.api.customers.dto.SecretDTO;
import com.doklad.api.customers.models.Document;
import com.doklad.api.customers.models.Secret;
import com.doklad.api.customers.models.User;
import com.doklad.api.customers.services.DocumentService;
import com.doklad.api.customers.services.SecretService;
import com.doklad.api.customers.services.UserService;
import com.doklad.api.customers.utility.exception.documentExceptions.DocumentNotFoundException;
import com.doklad.api.customers.utility.secrets.SecretValueGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;
    private final UserService userService;

    private final SecretService secretService;
    private final ModelMapper modelMapper;

    @Autowired
    public DocumentController(DocumentService documentService, SecretService secretService, ModelMapper modelMapper, UserService userService) {
        this.documentService = documentService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.secretService = secretService;
    }

    @GetMapping("/")
    public ResponseEntity<List<DocumentDTO>> findAll() {
        List<Document> users = documentService.findAll();
        List<DocumentDTO> userDTOs = users.stream().map(this::convertToDto).collect(Collectors.toList());

        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentDTO> findById(@PathVariable(name = "id") Long id) {
        Optional<Document> document = documentService.findById(id);

        if (document.isEmpty())
            throw new DocumentNotFoundException("Document with id " + id.toString() + " was not found");

        return ResponseEntity.ok(convertToDto(document.get()));
    }

    // Write exception handler for update method
    @PostMapping("/")
    public ResponseEntity<DocumentDTO> save(@RequestBody DocumentDTO documentDTO) {

        Optional<User> user = userService.findById(documentDTO.getUser());

        if (user.isEmpty())
            return ResponseEntity.notFound().build();

        Document document = new Document();
        document.setUser(user.get());
        document = convertToEntity(documentDTO);
        document = documentService.save(document);

        return ResponseEntity.ok(convertToDto(document));
    }


    // Write exception handler for update method
    @PutMapping("/{id}")
    public ResponseEntity<DocumentDTO> update(@PathVariable(name = "id") Long id) {

        Optional<Document> document = documentService.findById(id);

        if (document.isEmpty())
            throw new DocumentNotFoundException("Document with id " + id.toString() + " was not found");

        return document.map(value -> ResponseEntity.ok(convertToDto(value))).orElseGet(() -> ResponseEntity.notFound().build());

    }

    // Write exception handler for delete method
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "id") Long id) {
        Optional<Document> document = documentService.findById(id);

        if (document.isEmpty())
            throw new DocumentNotFoundException("Document with id " + id.toString() + " was not found");

        documentService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/secret")
    public ResponseEntity<SecretDTO> generateSecret(@PathVariable(name = "id") Long id) {
        Optional<Document> document = documentService.findById(id);

        if (document.isEmpty())
            throw new DocumentNotFoundException("Document with id " + id.toString() + " was not found");

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

    private Document convertToEntity(DocumentDTO documentDTO) {
        return modelMapper.map(documentDTO, Document.class);
    }
}
