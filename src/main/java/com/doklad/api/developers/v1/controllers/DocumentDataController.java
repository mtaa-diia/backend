package com.doklad.api.developers.v1.controllers;

import com.doklad.api.customers.dto.DocumentDTO;
import com.doklad.api.customers.models.Document;
import com.doklad.api.customers.services.DocumentService;
import com.doklad.api.developers.v1.dto.DocumentDataDTO;
import com.doklad.api.developers.v1.services.DocumentDataService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/document-data")
public class DocumentDataController {

    private final DocumentDataService documentDataService;
    private final DocumentService documentService;
    private ModelMapper modelMapper;

    @Autowired
    public DocumentDataController(DocumentDataService documentDataService, DocumentService documentService, ModelMapper modelMapper) {
        this.documentDataService = documentDataService;
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/create")
    public ResponseEntity<List<DocumentDataDTO>> createDocument(@RequestParam(name = "count", defaultValue = "1") int count) {
        List<Document> documents = documentDataService.generateDocumentNumber(count);

        List<DocumentDataDTO> documentDTOS = documents.stream().map(this::convertToDto).collect(Collectors.toList());

        documents.forEach(this.documentDataService::save);

        return ResponseEntity.ok(documentDTOS);
    }


    private DocumentDataDTO convertToDto(Document document) {
        return modelMapper.map(document, DocumentDataDTO.class);
    }

}
