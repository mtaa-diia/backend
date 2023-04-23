package com.doklad.api.controllers;

import com.doklad.api.customers.dto.UserDTO;
import com.doklad.api.customers.mappers.UserMapper;
import com.doklad.api.customers.models.Role;
import com.doklad.api.customers.models.User;
import com.doklad.api.customers.services.UserService;
import com.doklad.api.customers.utility.enums.RoleType;
import com.doklad.api.security.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private  MockMvc mockMvc;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private List<User> users = new ArrayList<>();
    private List<UserDTO> userDTOs = new ArrayList<>();


    @Mock
    private UserService userService;
    private String token;



    @BeforeEach
    public void setup() {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.token = jwtUtil.generateToken("JodyHeathcote");
        this.jwtUtil.validateToken(token);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);


        Collections.addAll(this.users,
                new User( 1L, "John", "Doe", "johndoe", "password", "email@gmail.com", new Role(RoleType.USER)),
                new User( 2L, "Jane", "Doe", "janedoe", "password", "gg@mail.ru", new Role(RoleType.USER)),
                new User( 3L, "John", "Smith", "johnsmith", "password", "dfgdfgg@gmail.com", new Role(RoleType.USER)),
                new User( 4L, "Jane", "Smith", "janesmith", "password", "test@gmail.com", new Role(RoleType.ADMIN)),
                new User( 5L, "John", "Doe", "johndoe", "password", "hh@gmail.com", new Role(RoleType.STAFF)),
                new User( 6L, "Jane", "Doe", "janedoe", "password", "rr@gmail.com" , new Role(RoleType.STAFF)));

        this.userDTOs = this.users.stream().map(userMapper::convertUserToUserDTO).toList();

    }

    @Test
    @Disabled
    public void findAllShouldReturnListOfUsersTest() throws Exception {

        when(this.userService.findAll()).thenReturn(users);

        MvcResult mvcResult = mockMvc.perform(get("http://localhost:6668/api/users/")
                .header("Authorization", "Bearer " + token)
        ).andExpect(status().isOk()).andReturn();

        String expectedJSON = new ObjectMapper().writeValueAsString(this.userDTOs);
        String actualJSON = mvcResult.getResponse().getContentAsString();

        JSONAssert.assertEquals(expectedJSON, actualJSON, false);
    }
}
