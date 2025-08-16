package com.example.demo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository repo;
    public UserService(UserRepository repo){ this.repo = repo; }

    @Override
    public UserDetails loadUserByUsername(String username) {
        var u = this.repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not found"));
        var auths = u.getRoles().stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.name()))
                .toList();
        return new org.springframework.security.core.userdetails.User(
                u.getUsername(), "", true, true, true, true, auths);
    }
}