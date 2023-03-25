package com.doklad.api.services;

import com.doklad.api.models.Status;
import com.doklad.api.repo.StatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StatusService {

    private final StatusRepo statusRepo;

    @Autowired
    public StatusService(StatusRepo statusRepo) {
        this.statusRepo = statusRepo;
    }

    public List<Status> findAll() {
        return statusRepo.findAll();
    }

    public Optional<Status> findById(Long id) {
        return statusRepo.findById(id);
    }

    @Transactional
    public Status save(Status status) {
        status.setCreatedAt(new Date());
        status.setUpdatedAt(new Date());
        return statusRepo.save(status);
    }

    @Transactional
    public void update(Status status) {
        status.setUpdatedAt(new Date());
        statusRepo.save(status);
    }

    @Transactional
    public void deleteById(Long id) {
        statusRepo.deleteById(id);
    }

}
