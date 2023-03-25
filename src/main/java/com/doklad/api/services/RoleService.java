package com.doklad.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.doklad.api.models.Role;
import com.doklad.api.repo.RoleRepo;

import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
@Transactional(readOnly = true)
public class RoleService {

    private final RoleRepo roleRepo;

    @Autowired
    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    public List<Role> findAll() {
        return roleRepo.findAll();
    }

    public Optional<Role> findById(Long id) {
        return roleRepo.findById(id);
    }

    @Transactional
    public Role save(Role role) {
        role.setCreatedAt(new Date());
        role.setUpdatedAt(new Date());
        return roleRepo.save(role);
    }

    @Transactional
    public void update(Role role) {
        role.setUpdatedAt(new Date());
        roleRepo.save(role);
    }

    @Transactional
    public void deleteById(Long id) {
        roleRepo.deleteById(id);
    }
}
