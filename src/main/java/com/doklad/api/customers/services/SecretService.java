package com.doklad.api.customers.services;

import com.doklad.api.customers.models.Secret;
import com.doklad.api.customers.repo.SecretRepository;
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
    private final SecretRepository secretRepository;

    @Autowired
    public SecretService(SecretRepository secretRepository) {
        this.secretRepository = secretRepository;
    }

    public Optional<Secret> findByValue(String secretValue) {
        return secretRepository.findSecretByValue(secretValue);
    }

    public List<Secret> findAll() {
        return secretRepository.findAll();
    }

    @Transactional
    public Secret save(Secret secret) {
        secret.setCreatedAt(new Date());
        // the secret expires in 30 minutes
        secret.setExpiresAt(DateUtils.addMinutes(new Date(), 30));

        return secretRepository.save(secret);
    }

    @Transactional
    public void deleteById(Long id) {
        secretRepository.deleteById(id);
    }
}
