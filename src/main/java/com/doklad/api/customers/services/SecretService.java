package com.doklad.api.customers.services;

import com.doklad.api.customers.models.Secret;
import com.doklad.api.customers.repo.SecretRepo;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SecretService {
    private final SecretRepo secretRepo;

    @Autowired
    public SecretService(SecretRepo secretRepo) {
        this.secretRepo = secretRepo;
    }

    public List<Secret> findByDocument(Long documentId) {
        return secretRepo.findSecretByDocumentId(documentId);
    }

    public Optional<Secret> findByValue(String secretValue) {
        return secretRepo.findSecretByValue(secretValue);
    }

    @Transactional
    public Secret save(Secret secret) {
        secret.setCreatedAt(new Date());
        // the secret expires in 30 minutes
        secret.setExpiresAt(DateUtils.addMinutes(new Date(), 30));

        return secretRepo.save(secret);
    }

    @Transactional
    public void deleteById(Long id) {
        secretRepo.deleteById(id);
    }
}
