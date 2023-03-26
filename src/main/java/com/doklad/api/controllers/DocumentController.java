package com.doklad.api.controllers;

import com.doklad.api.dto.DocumentDTO;
import com.doklad.api.dto.UserDTO;
import com.doklad.api.models.Document;
import com.doklad.api.models.User;
import com.doklad.api.services.DocumentService;
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
    private final ModelMapper modelMapper;

    @Autowired
    public DocumentController(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<DocumentDTO>> findAll() {
        List<Document> users = documentService.findAll();
        List<DocumentDTO> userDTOs = users.stream().map(this::convertToDto).collect(Collectors.toList());

        return ResponseEntity.ok(userDTOs);
    }

    // Write exception handler for findById
    @GetMapping("/{id}")
    public ResponseEntity<DocumentDTO> findById(@PathVariable(name = "id") Long id) {
        Optional<Document> document = documentService.findById(id);

        return ResponseEntity.ok(convertToDto(document.get()));
    }

    // Write exception handler for update method
    @PostMapping("/")
    public ResponseEntity<DocumentDTO> save(@RequestBody DocumentDTO documentDTO) {
        Document document = convertToEntity(documentDTO);
        document = documentService.save(document);

        return ResponseEntity.ok(convertToDto(document));
    }


    // Write exception handler for update method
    @PutMapping("/{id}")
    public ResponseEntity<DocumentDTO> update(@PathVariable(name = "id") Long id, @RequestBody DocumentDTO documentDTO) {

        Optional<Document> document = documentService.findById(id);

        return document.map(value -> ResponseEntity.ok(convertToDto(value))).orElseGet(() -> ResponseEntity.notFound().build());

    }

    // Write exception handler for delete method
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "id") Long id) {
        Optional<Document> document = documentService.findById(id);

        if (document.isEmpty())
            return ResponseEntity.notFound().build();

        documentService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    private DocumentDTO convertToDto(Document document) {
        return modelMapper.map(document, DocumentDTO.class);
    }

    private Document convertToEntity(DocumentDTO documentDTO) {
        return modelMapper.map(documentDTO, Document.class);
    }
}
