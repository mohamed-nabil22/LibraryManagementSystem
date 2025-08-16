package com.example.demo;

import com.example.demo.role.Role;
import com.example.demo.role.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public RoleInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if roles already exist
        if (roleRepository.findByName("ADMIN").isEmpty()) {
            Role adminRole = new Role();
            adminRole.setName("ADMIN");
            roleRepository.save(adminRole);
        }

        if (roleRepository.findByName("LIBRARIAN").isEmpty()) {
            Role librarianRole = new Role();
            librarianRole.setName("LIBRARIAN");
            roleRepository.save(librarianRole);
        }

        if (roleRepository.findByName("STAFF").isEmpty()) {
            Role staffRole = new Role();
            staffRole.setName("STAFF");
            roleRepository.save(staffRole);
        }

        if (roleRepository.findByName("MEMBER").isEmpty()) {
            Role memberRole = new Role();
            memberRole.setName("MEMBER");
            roleRepository.save(memberRole);
        }

        System.out.println("Roles initialized successfully!");
    }
}
