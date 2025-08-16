package com.example.demo.Book;

public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException(String message) {
        super(message);
    }
}
