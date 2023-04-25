package com.doklad.api.customers.services;

import com.doklad.api.customers.repo.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
@Transactional(readOnly = true)
public class ServiceService {

    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<com.doklad.api.customers.models.Service> findAll() {
        return serviceRepository.findAll();
    }

    public Optional<com.doklad.api.customers.models.Service> findById(Long id) {
        return serviceRepository.findById(id);
    }

    @Transactional
    public com.doklad.api.customers.models.Service save(com.doklad.api.customers.models.Service service) {
        service.setCreatedAt(new Date());
        service.setUpdatedAt(new Date());
        return serviceRepository.save(service);
    }

    @Transactional
    public com.doklad.api.customers.models.Service update(com.doklad.api.customers.models.Service service) {
        service.setUpdatedAt(new Date());
        return serviceRepository.save(service);
    }

    @Transactional
    public void deleteById(Long id) {
        serviceRepository.deleteById(id);
    }
}
