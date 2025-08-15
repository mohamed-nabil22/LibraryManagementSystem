package com.example.demo.bookTransaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookTransactionRepository extends JpaRepository<BookTransaction, Long> {

}
