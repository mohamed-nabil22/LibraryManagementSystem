package com.example.demo.bookTransaction;

import com.example.demo.Book.BookService;
import com.example.demo.Member.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;

@Service
@AllArgsConstructor
public class BookTransactionService {

    MemberService memberService;
    BookService bookService;
    BookTransactionRepository bookTransactionRepository;

    private void createTransaction(Long memberId, Long BookId ,BookTransactionStatus bookTransactionStatus)
    {
        BookTransaction bookTransaction = new BookTransaction();
        bookTransaction.setBook(bookService.getBookById(BookId));
        bookTransaction.setMember(memberService.getMemberById(memberId));
        bookTransaction.setStatus(bookTransactionStatus);
        bookTransaction.setDate(Instant.now());
        bookTransactionRepository.save(bookTransaction);
    }

    public boolean borrowBook(@RequestBody BookTransactionDto bookTransactionDto) {
        if(bookService.borrowBook(bookTransactionDto.getBookId())) {
            createTransaction(bookTransactionDto.memberId, bookTransactionDto.bookId, BookTransactionStatus.BORROWED);
            return true;
        }
        return false;

    }

    public boolean returnBook(@RequestBody BookTransactionDto bookTransactionDto) {
        if (bookService.returnBook(bookTransactionDto.getBookId())) {
            createTransaction(bookTransactionDto.memberId, bookTransactionDto.bookId, BookTransactionStatus.RETURNED);
            return true;
        }
        return false;
    }



}
