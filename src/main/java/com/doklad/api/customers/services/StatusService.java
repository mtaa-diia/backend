package com.doklad.api.customers.services;

import com.doklad.api.customers.models.Status;
import com.doklad.api.customers.repo.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StatusService {

    private final StatusRepository statusRepository;

    @Autowired
    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<Status> findAll() {
        return statusRepository.findAll();
    }

    public Optional<Status> findById(Long id) {
        return statusRepository.findById(id);
    }

    @Transactional
    public Status save(Status status) {
        status.setCreatedAt(new Date());
        status.setUpdatedAt(new Date());
        return statusRepository.save(status);
    }

    @Transactional
    public Status update(Status status) {
        status.setUpdatedAt(new Date());
        return statusRepository.save(status);
    }

    @Transactional
    public void deleteById(Long id) {
        statusRepository.deleteById(id);
    }

}
