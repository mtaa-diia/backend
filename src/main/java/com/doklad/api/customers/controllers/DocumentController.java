package com.doklad.api.customers.controllers;

import com.doklad.api.customers.dto.DocumentDTO;
import com.doklad.api.customers.mappers.DocumentMapper;
import com.doklad.api.customers.models.Document;
import com.doklad.api.customers.models.User;
import com.doklad.api.customers.services.DocumentService;
import com.doklad.api.customers.services.UserService;
import com.doklad.api.customers.utility.exception.documentExceptions.DocumentAlreadyExistException;
import com.doklad.api.customers.utility.exception.documentExceptions.DocumentNotFoundException;
import com.doklad.api.customers.utility.exception.userExceptions.UserNotFoundException;
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

    private final DocumentMapper documentMapper;

    @Autowired
    public DocumentController(DocumentService documentService, DocumentMapper documentMapper, UserService userService) {
        this.documentService = documentService;
        this.userService = userService;
        this.documentMapper = documentMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<DocumentDTO>> findAll() {
        List<Document> documents = documentService.findAll();

        if (documents.isEmpty())
            throw new DocumentNotFoundException("No documents were found");


        List<DocumentDTO> documentDTOs = documents.stream().map(this.documentMapper::convertToDto).collect(Collectors.toList());

        return ResponseEntity.ok(documentDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentDTO> findById(@PathVariable(name = "id") Long id) {
        Optional<Document> document = documentService.findById(id);
        DocumentDTO documentDTO = new DocumentDTO();

        if (document.isEmpty())
            throw new DocumentNotFoundException("Document with id " + id.toString() + " was not found");

        documentDTO = documentMapper.convertToDto(document.get());

        return ResponseEntity.ok(documentDTO);
    }

    @PostMapping("/")
    public ResponseEntity<DocumentDTO> save(@RequestBody DocumentDTO documentDTO) {
        long userId = documentDTO.getUserId();
        Optional<User> user = userService.findById(userId);
        Optional<Document> document = documentService.findByTitleAndContentAndDescription(documentDTO.getTitle(), documentDTO.getContent(), documentDTO.getDescription());
        Document newDocument = new Document();
        DocumentDTO savedDocumentDTO = new DocumentDTO();

        if (user.isEmpty())
            throw new UserNotFoundException("User with id " + userId + " was not found");

        if (document.isPresent())
            throw new DocumentAlreadyExistException("Document with title " + documentDTO.getTitle() + " already exists");

        newDocument = documentMapper.convertToEntity(documentDTO);
        newDocument.setUser(user.get());
        documentService.save(newDocument);
        savedDocumentDTO = documentMapper.convertToDto(newDocument);

        return ResponseEntity.ok(savedDocumentDTO);
    }


    @PutMapping("/")
    public ResponseEntity<DocumentDTO> update(@RequestBody DocumentDTO documentDTO) {
        long id = documentDTO.getId();
        Optional<Document> document = documentService.findById(id);
        Document updatedDocument = documentMapper.convertToEntity(documentDTO);
        Optional<User> user = userService.findById(documentDTO.getUserId());

        if (user.isEmpty())
            throw new UserNotFoundException("User with id " + documentDTO.getUserId().toString() + " was not found");

        if (document.isEmpty())
            throw new DocumentNotFoundException("Document with id " + id + " was not found");

        documentService.update(updatedDocument);

        return ResponseEntity.ok(documentDTO);
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

}
