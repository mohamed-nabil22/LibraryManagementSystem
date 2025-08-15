package com.example.demo.member;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto increment id
    private Long id;

    @Column(nullable = false)
    private String name;

    private String email;
    private String address;
    private String phoneNumber;

}
