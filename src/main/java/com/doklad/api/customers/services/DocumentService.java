package com.doklad.api.customers.services;

import com.doklad.api.customers.models.Document;
import com.doklad.api.customers.models.Status;
import com.doklad.api.customers.repo.DocumentRepository;
import com.doklad.api.customers.utility.enums.StatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class DocumentService {
    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public List<Document> findAll() {
        return documentRepository.findAll();
    }

    public Optional<Document> findById(Long id) {
        return documentRepository.findById(id);
    }

    @Transactional
    public Document save(Document document) {
        document.setCreatedAt(new Date());
        document.setUpdatedAt(new Date());
        document.setStatus(new Status(StatusType.PENDING));
        return documentRepository.save(document);
    }

    @Transactional
    public void update(Document document) {
        document.setUpdatedAt(new Date());
        document.setStatus(new Status(StatusType.PENDING));
        documentRepository.save(document);
    }

    @Transactional
    public void deleteById(Long id) {
        documentRepository.deleteById(id);
    }

    public Optional<Document> findByTitleAndContentAndDescription(String title, String content, String description) {
        return documentRepository.findByTitleAndContentAndDescription(title, content, description);
    }
}
