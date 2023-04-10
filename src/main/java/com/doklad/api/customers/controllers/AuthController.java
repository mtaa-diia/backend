package com.doklad.api.customers.controllers;

import com.doklad.api.customers.dto.AuthenticationDTO;
import com.doklad.api.customers.models.User;
import com.doklad.api.customers.services.UserService;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(UserService userService, JWTUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;

    }

    // Login api method
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid AuthenticationDTO authenticationDTO, BindingResult bindingResult) {
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(), authenticationDTO.getPassword());

        String jwtToken = "";

        System.out.println(SecurityContextHolder.getContext().getAuthentication());

        Optional<User> authUser = userService.findByUsername(authenticationDTO.getUsername());

        // Binding of errors from DTO
        if (bindingResult.hasErrors() || authUser.isEmpty()) {
            return new ResponseEntity<>("Incorrect username or password.", HttpStatus.BAD_REQUEST);
        }

        // Authentication of user
        try {
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException exception) {
            System.out.println("Authentication failed: " + exception);
            throw new BadCredentialsException("Incorrect username or password.");
        } finally {
            System.out.println("Authentication status:" + SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
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

    // Registration api method


}
