package com.doklad.api.developers.v1.services;

import com.doklad.api.customers.models.Role;
import com.doklad.api.customers.models.User;
import com.doklad.api.customers.services.RoleService;
import com.doklad.api.customers.services.UserService;
import com.doklad.api.customers.utility.enums.RoleType;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

@Service
@Transactional(readOnly = true)
public class UserDataService {

    private final Faker faker;
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserDataService(Faker faker, UserService userService, RoleService roleService) {
        this.faker = faker;
        this.userService = userService;
        this.roleService = roleService;
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
                password = faker.internet().password();

            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setUsername(firstName + lastName);
            user.setEmail(email);
            user.setPassword(password);

            // Create role
            Role role = new Role(RoleType.USER);
            roleService.save(role);

            // Set role
            user.setRole(role);

            user.setCreatedAt(new Date());
            user.setUpdatedAt(new Date());
            users.add(user);
        });


        return users;
    }

}
