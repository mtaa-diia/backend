package com.doklad.api.customers.services;

import com.doklad.api.customers.repo.ServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
@Transactional(readOnly = true)
public class ServiceService {

    private final ServiceRepo serviceRepo;

    @Autowired
    public ServiceService(ServiceRepo serviceRepo) {
        this.serviceRepo = serviceRepo;
    }

    public List<com.doklad.api.customers.models.Service> findAll() {
        return serviceRepo.findAll();
    }

    public Optional<com.doklad.api.customers.models.Service> findById(Long id) {
        return serviceRepo.findById(id);
    }

    @Transactional
    public com.doklad.api.customers.models.Service save(com.doklad.api.customers.models.Service service) {
        service.setCreatedAt(new Date());
        service.setUpdatedAt(new Date());
        return serviceRepo.save(service);
    }

    @Transactional
    public com.doklad.api.customers.models.Service update(com.doklad.api.customers.models.Service service) {
        service.setUpdatedAt(new Date());
        return serviceRepo.save(service);
    }

    @Transactional
    public void deleteById(Long id) {
        serviceRepo.deleteById(id);
    }
}