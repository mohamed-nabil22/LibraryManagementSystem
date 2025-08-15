package com.example.demo.bookTransaction;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BookTransactionController {

    private BookTransactionService bookTransactionService;

    @PostMapping("/borrow")
    public boolean borrowBook(@RequestBody BookTransactionDto bookTransactionDto) {
        return bookTransactionService.borrowBook(bookTransactionDto);
    }

    @PostMapping("/return")
    public boolean returnBook(@RequestBody BookTransactionDto bookTransactionDto) {
        return bookTransactionService.returnBook(bookTransactionDto);
    }
}
