package com.doklad.api.developers.v1.services;

import com.doklad.api.customers.models.Role;
import com.doklad.api.customers.models.User;
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

    @Autowired
    public UserDataService(Faker faker, UserService userService) {
        this.faker = faker;
        this.userService = userService;
    }

    @Transactional
    public List<User> generateUsers(int count) {
        List<User> users = new ArrayList<>();

        IntStream.range(1, count).forEach(i -> {
            User user = new User();
            user.setFirstName(faker.name().firstName());
            user.setLastName(faker.name().lastName());
            user.setEmail(faker.internet().emailAddress());
            user.setPassword(faker.internet().password());
            user.setUsername(faker.name().username());
            Role role = new Role(RoleType.USER);
            user.setRole(role);

            user.setCreatedAt(new Date());
            user.setUpdatedAt(new Date());
            users.add(user);
        });

        users.forEach(userService::save);


        return users;
    }

}
