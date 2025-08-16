package com.example.demo.auth;

import com.example.demo.User.User;
import com.example.demo.User.UserRepository;
import com.example.demo.role.Role;
import com.example.demo.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class UserRegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register/staff")
    public String registerStaff(@RequestBody UserRegistrationRequest request) {
        // Check if the user already exists
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return "Staff already exists!";
        }

        // Create new user
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));  // Encode the password

        // Assign roles (e.g., default role "STAFF")
        Role role = roleRepository.findByName("STAFF").orElseThrow();
        newUser.setRoles(Set.of(role));

        userRepository.save(newUser);

        return "User registered successfully!";
    }
    @PostMapping("/register/admin")
    public String registerAdmin(@RequestBody UserRegistrationRequest request) {
        // Check if the user already exists
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return "Admin already exists!";
        }

        // Create new user
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));  // Encode the password

        // Assign roles (e.g., default role "STAFF")
        Role role = roleRepository.findByName("ADMIN").orElseThrow();
        newUser.setRoles(Set.of(role));

        userRepository.save(newUser);

        return "Admin registered successfully!";
    }
    @PostMapping("/register/librarian")
    public String registerLibrarian(@RequestBody UserRegistrationRequest request) {
        // Check if the user already exists
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return "Staff already exists!";
        }

        // Create new user
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));  // Encode the password

        // Assign roles (e.g., default role "STAFF")
        Role role = roleRepository.findByName("LIBRARIAN").orElseThrow();
        newUser.setRoles(Set.of(role));

        userRepository.save(newUser);

        return "Librarian registered successfully!";
    }
}
