package com.doklad.api.developers.v1.services;

import com.doklad.api.customers.models.Role;
import com.doklad.api.customers.models.User;
import com.doklad.api.customers.repo.UserRepo;
import com.doklad.api.customers.services.RoleService;
import com.doklad.api.customers.services.UserService;
import com.doklad.api.customers.utility.enums.RoleType;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.IntStream;

@Service
@Transactional(readOnly = true)
public class UserDataService {

    private final Faker faker;
    private final RoleService roleService;
    private final UserRepo userRepo;

    @Autowired
    public UserDataService(Faker faker, RoleService roleService, UserRepo userRepo) {
        this.faker = faker;
        this.roleService = roleService;
        this.userRepo = userRepo;
    }

    @Transactional
    public List<User> generateUsers(int count) {
        List<User> users = new ArrayList<>();

        IntStream.range(0, count).forEach(i -> {
            User user = new User();

            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = faker.internet().emailAddress();
            String password = faker.internet().password();

            while (firstName.length() >= 255 || firstName.length() <= 3)
                firstName = faker.name().firstName();

            while (lastName.length() >= 255 || lastName.length() <= 3)
                lastName = faker.name().lastName();

            while (email.length() >= 255 || email.length() <= 3)
                email = faker.internet().emailAddress();

            while (password.length() >= 255 || password.length() <= 3)
                password = "test123";

            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setUsername(firstName + lastName);
            user.setEmail(email);
            user.setPassword("test123");

            // Create role
            Role role = new Role(RoleType.USER);
            role.setUsers(Collections.singletonList(user));
            roleService.save(role);
            user.setRole(role);
            users.add(user);
        });


        return users;
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Transactional
    public User save(User user) {
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        return userRepo.save(user);
    }

    public long count() {
        return userRepo.count();
    }

    public Optional<User> findById(long number) {
        return userRepo.findById(number);
    }



    @Transactional
    public void update(User user) {
        user.setUpdatedAt(new Date());
        userRepo.save(user);
    }
}
