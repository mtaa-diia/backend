package com.doklad.api.customers.services;

import com.doklad.api.customers.models.Document;
import com.doklad.api.customers.models.Status;
import com.doklad.api.customers.repo.DocumentRepo;
import com.doklad.api.customers.utility.enums.StatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class DocumentService {
    private final DocumentRepo documentRepo;

    @Autowired
    public DocumentService(DocumentRepo documentRepo) {
        this.documentRepo = documentRepo;
    }

    public List<Document> findAll() {
        return documentRepo.findAll();
    }

    public Optional<Document> findById(Long id) {
        return documentRepo.findById(id);
    }

    @Transactional
    public Document save(Document document) {
        document.setCreatedAt(new Date());
        document.setUpdatedAt(new Date());
        document.setStatus(new Status(StatusType.PENDING));
        return documentRepo.save(document);
    }

    @Transactional
    public void update(Document document) {
        document.setUpdatedAt(new Date());
        document.setStatus(new Status(StatusType.PENDING));
        documentRepo.save(document);
    }

    @Transactional
    public void deleteById(Long id) {
        documentRepo.deleteById(id);
    }

    public Optional<Document> findByTitleAndContentAndDescription(String title, String content, String description) {
        return documentRepo.findByTitleAndContentAndDescription(title, content, description);
    }
}
