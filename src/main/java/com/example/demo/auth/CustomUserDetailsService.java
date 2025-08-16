package com.example.demo.auth;

import com.example.demo.User.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the user from the database using the repository
        com.example.demo.User.User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Convert your custom User entity into Spring Security's UserDetails object
        return new User(
                user.getUsername(),  // Username
                user.getPassword(),  // Password (hashed in DB)
                user.getRoles().stream()  // Convert roles to authorities
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())) // Map each role to authority
                        .collect(Collectors.toList()) // Collect roles into a list
        );
    }
}