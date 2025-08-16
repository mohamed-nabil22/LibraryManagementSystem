package com.example.demo.bookTransaction;

import com.example.demo.Book.Book;
import com.example.demo.Member.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    Book book;

    @ManyToOne
    @JoinColumn(name = "member_id")
    Member member;

    @Enumerated(EnumType.STRING)
    BookTransactionStatus status;



    private Instant date;
}
