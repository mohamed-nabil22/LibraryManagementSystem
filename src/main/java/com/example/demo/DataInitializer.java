//package com.example.demo;
//
//import com.example.demo.Admin.Admin;
//import com.example.demo.Admin.AdminRepository;
//import com.example.demo.Member.Member;
//import com.example.demo.Member.MemberRepository;
//import com.example.demo.User.User;
//import com.example.demo.User.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//
//@Configuration
//public class DataInitializer {
//
//    @Bean
//    CommandLineRunner initData(UserRepository userRepo,
//                               MemberRepository memberRepo,
//                               AdminRepository adminRepo,
//                               PasswordEncoder encoder) {
//        return args -> {
//            System.out.println("Adding default data (idempotent)…");
//
//            ensureAdmin(userRepo, adminRepo, encoder);
//            ensureMember(userRepo, memberRepo, encoder);
//
//            System.out.println("✅ Defaults ensured (admin + member).");
//        };
//    }
//
//    private void ensureAdmin(UserRepository userRepo,
//                             AdminRepository adminRepo,
//                             PasswordEncoder encoder) {
//
//        // 1) Ensure admin user exists
//        User adminUser = userRepo.findByUsername("admin").orElseGet(() -> {
//            User u = new User();
//            u.setUsername("admin");
//            u.setPasswordHash(encoder.encode("admin123"));
//            u.getRoles().add(Role.ADMIN);
//            return userRepo.save(u);
//        });
//
//        // 2) Ensure admin user has ADMIN role (in case user existed without role)
//        if (!adminUser.getRoles().contains(Role.ADMIN)) {
//            adminUser.getRoles().add(Role.ADMIN);
//            userRepo.save(adminUser);
//        }
//
//        // 3) Ensure Admin profile exists and is linked
//        if (!adminRepo.existsByUserUsername("admin")) {
//            Admin admin = new Admin();
//            admin.setUser(adminUser);
//            adminRepo.save(admin);
//        }
//    }
//
//    private void ensureMember(UserRepository userRepo,
//                              MemberRepository memberRepo,
//                              PasswordEncoder encoder) {
//
//        // 1) Ensure member user exists
//        User memberUser = userRepo.findByUsername("member").orElseGet(() -> {
//            User u = new User();
//            u.setUsername("member");
//            u.setPasswordHash(encoder.encode("member123"));
//            u.getRoles().add(Role.MEMBER);
//            return userRepo.save(u);
//        });
//
//        // 2) Ensure MEMBER role is present
//        if (!memberUser.getRoles().contains(Role.MEMBER)) {
//            memberUser.getRoles().add(Role.MEMBER);
//            userRepo.save(memberUser);
//        }
//
//        // 3) Ensure Member profile exists and is linked
//        if (!memberRepo.existsByUserUsername("member")) {
//            Member m = new Member();
//            m.setUser(memberUser);
//            m.setEmail("member@member.com");
//            m.setAddress("123 street, Cairo");
//            m.setPhoneNumber("+100");
//            memberRepo.save(m);
//        }
//    }
//}
