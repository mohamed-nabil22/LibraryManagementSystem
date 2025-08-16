package com.example.demo.auth;

import lombok.Data;

@Data
public class MemberRegistrationRequest {
    private String username;
    private String password;
    private String email;
    private String address;
    private String phoneNumber;
}
