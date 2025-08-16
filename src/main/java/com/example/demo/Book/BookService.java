package com.example.demo.Book;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    // Method to save a book
    public Book saveBook(Book book) {
        if(bookRepository.existsByIsbn(book.getIsbn()))
            throw new BookAlreadyExistsException("A book with ISBN " + book.getIsbn() + " already exists.");
        else return bookRepository.save(book); //save it if it doesnt exist
    }

    public void deleteBook(Long bookId) {
      boolean exists = bookRepository.existsById(bookId);
      if(!exists)
          throw new IllegalStateException("Book with Id " + bookId + " does not exist.");
      else bookRepository.deleteById(bookId);
    }

    public void updateBook(Long bookId, Book updatedBook) {

        if(!bookRepository.existsById(bookId))
            throw new IllegalStateException("Book with Id " + bookId + " does not exist.");
        Book book = bookRepository.findBookById(bookId).get();

        book.setTitle(updatedBook.getTitle());
        book.setBookAuthors(updatedBook.getBookAuthors());
        book.setIsbn(updatedBook.getIsbn());
        book.setSummary(updatedBook.getSummary());
        book.setEdition(updatedBook.getEdition());
        book.setPublisher(updatedBook.getPublisher());
        book.setCoverImageUrl(updatedBook.getCoverImageUrl());
        book.setCategories(updatedBook.getCategories());
        book.setLanguage(updatedBook.getLanguage());
        book.setPublicationYear(updatedBook.getPublicationYear());

        bookRepository.save(book);

    }


    public Book getBookById(Long bookId) {
        if(!bookRepository.existsById(bookId))
            throw new IllegalStateException("Book with Id " + bookId + " does not exist.");
        else return bookRepository.findBookById(bookId).get();
    }

    public Boolean borrowBook(Long bookId) {
        if(!bookRepository.existsById(bookId))
            throw new IllegalStateException("Book with Id " + bookId + " does not exist.");

        Book book = bookRepository.findBookById(bookId).get();
        if(book.getAvailableQuantity() > 0)
        {
            book.setAvailableQuantity(book.getAvailableQuantity() - 1);
            bookRepository.save(book);
            return true;
        }
        return false;
    }
    public Boolean returnBook(Long bookId) {
        if(!bookRepository.existsById(bookId))
          throw new IllegalStateException("Book with Id " + bookId + " does not exist.");

        Book book = bookRepository.findBookById(bookId).get();
        book.setAvailableQuantity(book.getAvailableQuantity() + 1);
        bookRepository.save(book);
        return true;
    }
}
