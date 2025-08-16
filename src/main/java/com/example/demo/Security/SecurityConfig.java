package com.example.demo.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filter(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF protection (for REST APIs)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/auth/register/admin").permitAll() // for debugging
                        .requestMatchers("/api/auth/register/staff").hasRole("ADMIN")
                        .requestMatchers("/api/auth/register/librarian").hasAnyRole("ADMIN")
                        .requestMatchers("/api/auth/register/member").hasAnyRole("ADMIN", "LIBRARIAN", "STAFF")
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/librarian/**").hasAnyRole("ADMIN", "LIBRARIAN")
                        .requestMatchers("/api/staff/**").hasAnyRole("ADMIN", "LIBRARIAN", "STAFF")
                        .requestMatchers("/books/staff/**").hasAnyRole("ADMIN", "LIBRARIAN", "STAFF")
                        .anyRequest().authenticated()  // All other requests must be authenticated
                )
                .formLogin(withDefaults())  // Default form login configuration
                .httpBasic(withDefaults());  // Optional: HTTP Basic authentication for API testing

        return http.build();
    }
}
