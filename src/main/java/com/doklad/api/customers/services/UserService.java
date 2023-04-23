package com.doklad.api.customers.services;

import com.doklad.api.customers.models.Role;
import com.doklad.api.customers.models.User;
import com.doklad.api.customers.repo.UserRepo;
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

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepo userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
