package com.doklad.api.security;

import com.doklad.api.customers.models.User;
import com.doklad.api.customers.utility.enums.RoleType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class MyUserDetails implements UserDetails {

    private final User user;

    public MyUserDetails(User user) {
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.user.getRole().getRole().equals(RoleType.ADMIN))
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        else if (this.user.getRole().getRole().equals(RoleType.USER))
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        else if (this.user.getRole().getRole().equals(RoleType.STAFF))
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_STAFF"));

        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
