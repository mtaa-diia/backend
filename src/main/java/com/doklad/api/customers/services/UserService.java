package com.doklad.api.customers.services;

import com.doklad.api.customers.models.Role;
import com.doklad.api.customers.models.User;
import com.doklad.api.customers.repo.UserRepository;
import com.doklad.api.customers.utility.enums.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;
import java.util.Date;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            user.setRole(new Role(RoleType.USER));
        }
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public long count() {
        return userRepository.count();
    }

    @Transactional
    public User save(User user) {

        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        user.setRole(new Role(RoleType.USER));


        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    public User update(User user) {
        user.setUpdatedAt(new Date());
        return userRepository.save(user);
    }

    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }


    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
