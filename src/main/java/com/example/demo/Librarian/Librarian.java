package com.example.demo.Librarian;

import com.example.demo.User.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity @Table(name = "librarians")
public class Librarian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto increment id
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", unique = true)
    private User user;


}
