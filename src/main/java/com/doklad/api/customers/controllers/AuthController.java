package com.doklad.api.customers.controllers;

import com.doklad.api.customers.dto.AuthenticationDTO;
import com.doklad.api.customers.dto.RegistrationDTO;
import com.doklad.api.customers.mappers.UserMapper;
import com.doklad.api.customers.models.Role;
import com.doklad.api.customers.models.User;
import com.doklad.api.customers.services.UserService;
import com.doklad.api.customers.utility.enums.RoleType;
import com.doklad.api.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    @Autowired
    public AuthController(UserService userService, JWTUtil jwtUtil, AuthenticationManager authenticationManager, UserMapper userMapper) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;

    }

    // Login api method
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid AuthenticationDTO authenticationDTO, BindingResult bindingResult) {

        String jwtToken = "";
        Optional<User> authUser = userService.findByUsername(authenticationDTO.getUsername());

        System.out.println("Is user authenticated: " + SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
        System.out.println("Authorities : " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());

        // Binding of errors from DTO
        if (bindingResult.hasErrors() || authUser.isEmpty())
            return new ResponseEntity<>("Incorrect username or password.", HttpStatus.BAD_REQUEST);


        Authentication authentication =
                new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(), authenticationDTO.getPassword());
        // Authentication of user
        try {
            authenticationManager.authenticate(authentication);
            System.out.println("Authentication successful: " + authentication.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException exception) {
            System.out.println("Authentication failed: " + exception);
            throw new BadCredentialsException("Incorrect username or password.");
        }

        // isAnonymous() - true if user is not authenticated
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            System.out.println("Authorities: " + authorities);
        }

        // Generate token
        jwtToken = jwtUtil.generateToken(authUser.get().getUsername());
        System.out.println("Token: " + jwtToken);
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        System.out.println(authUser.get().getRole());

        return new ResponseEntity<>(jwtToken, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationDTO registrationDTO) {

        String jwtToken = "";
        Optional<User> authUser = userService.findByUsername(registrationDTO.getUsername());

        // Binding of errors from DTO
        if (authUser.isPresent())
            return new ResponseEntity<>("Incorrect username or password.", HttpStatus.BAD_REQUEST);

        // Create user
        User user = this.userMapper.convertRgeistrartionDTOToUser(registrationDTO);
        user.setPassword(registrationDTO.getPassword());
        Role role = new Role(RoleType.USER);
        user.setRole(role);

        // Save user to database
        userService.save(user);

        // Generate token
        jwtToken = jwtUtil.generateToken(user.getUsername());

        return new ResponseEntity<>(jwtToken, HttpStatus.OK);
    }

    // Registration api method
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException exception) {
        return new ResponseEntity<>("Incorrect credentials.\n" + exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
