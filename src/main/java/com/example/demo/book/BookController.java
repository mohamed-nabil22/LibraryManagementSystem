package com.example.demo.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping(path = "/books")
    public class BookController {

        private final BookService bookService;

        @Autowired
        public BookController(BookService bookService) {
            this.bookService = bookService;
        }
        //bookservice will be magically instantiated for us by dependency injection
        //so bookservice must be add @Component or @Service annotation
    @GetMapping //get mapping withou a path return allbooks
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("{BookId}")
    public ResponseEntity<Book> getBookById(@PathVariable("BookId") Long bookId) {
            Book book = bookService.getBookById(bookId);
            return ResponseEntity.ok(book);
    }

        @PostMapping
        public ResponseEntity<String> registerBook(@RequestBody Book book) {
            try {
                bookService.saveBook(book);
                return ResponseEntity.status(HttpStatus.CREATED).body("Book Created Successfully");
            } catch (BookAlreadyExistsException e) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Book Already Exists");
            }
        }
        @DeleteMapping(path = "{BookId}")
        public ResponseEntity<String> deleteBook(@PathVariable("BookId") Long BookId) {
            bookService.deleteBook(BookId);
            return ResponseEntity.ok("Book deleted successfully");
        }

        @PutMapping(path = "{BookId}")
        public ResponseEntity<String> updateBook(@PathVariable("BookId") Long BookId, @RequestBody Book book) {
            bookService.updateBook(BookId,book);
            return ResponseEntity.ok("Book updated successfully");
        }
    }

