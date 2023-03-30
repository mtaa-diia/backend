package com.doklad.api.customers.repo;

import com.doklad.api.customers.models.Secret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SecretRepo extends JpaRepository<Secret, Long> {
    List<Secret> findSecretByDocumentId(Long documentId);

    Optional<Secret> findSecretByValue(String value);
}
