package com.example.demo.auth;

import com.example.demo.Admin.Admin;
import com.example.demo.Admin.AdminRepository;
import com.example.demo.Librarian.Librarian;
import com.example.demo.Librarian.LibrarianRepository;
import com.example.demo.Member.Member;
import com.example.demo.Member.MemberRepository;
import com.example.demo.Staff.Staff;
import com.example.demo.Staff.StaffRepository;
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
    private AdminRepository adminRepository;

    @Autowired
    private LibrarianRepository librarianRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/register/member")
    public String registerMember(@RequestBody MemberRegistrationRequest request) {
        // Check if the user already exists
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return "Member already exists!";
        }

        // Create new user
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));  // Encode the password

        // Assign roles (e.g., default role "STAFF")
        Role role = roleRepository.findByName("MEMBER").orElseThrow();
        newUser.setRoles(Set.of(role));

        Member member = new Member();
        member.setPhoneNumber(request.getPhoneNumber());
        member.setEmail(request.getEmail());
        member.setAddress(request.getAddress());
        member.setUser(newUser);

        memberRepository.save(member);

        return "member registered successfully!";
    }

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

        Staff staff = new Staff();
        staff.setUser(newUser);

        staffRepository.save(staff);

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

        Admin admin = new Admin();
        admin.setUser(newUser);

        adminRepository.save(admin);

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


        Librarian librarian = new Librarian();
        librarian.setUser(newUser);

        librarianRepository.save(librarian);

        return "Librarian registered successfully!";
    }
}
