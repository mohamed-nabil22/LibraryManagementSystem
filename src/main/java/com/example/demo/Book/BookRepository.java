package com.example.demo.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    boolean existsByIsbn(String isbn);
    //@Query("select * from book where id=?1")
     Optional<Book> findBookById(Long id);
}
