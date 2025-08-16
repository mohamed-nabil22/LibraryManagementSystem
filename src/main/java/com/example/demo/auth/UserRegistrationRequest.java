package com.example.demo.auth;

import lombok.Data;

@Data
public class UserRegistrationRequest {
    private String username;
    private String password;
}
