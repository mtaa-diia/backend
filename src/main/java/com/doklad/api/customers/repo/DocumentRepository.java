package com.doklad.api.customers.repo;

import com.doklad.api.customers.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    Optional<Document> findByTitleAndContentAndDescription(String title, String content, String description);
}
