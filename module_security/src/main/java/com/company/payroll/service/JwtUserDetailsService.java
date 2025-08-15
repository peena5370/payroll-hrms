package com.company.payroll.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // maybe if really use it, need get the user roles, or any claims and set it to the local cache
        throw new UnsupportedOperationException("This method is not used for JWT authentication.");
    }

    // This is the method your filter should call.
    public UserDetails buildUser(String username, List<String> roles) {
        if (username == null) {
            throw new UsernameNotFoundException("Username is null");
        }

        // Convert the string roles into a collection of GrantedAuthority objects
        Collection<GrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        // We use an empty password because the password is not stored in the JWT
        return new User(username, "", authorities);
    }
}
