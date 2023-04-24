package com.doklad.api.customers.services;

import com.doklad.api.customers.models.User;
import com.doklad.api.customers.repo.UserRepository;
import com.doklad.api.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public MyUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty())
            throw new UsernameNotFoundException("User not found");

        MyUserDetails myUserDetails = new MyUserDetails(user.get());
        System.out.println("My Role: " + myUserDetails.getAuthorities());

        return myUserDetails;
    }
}
