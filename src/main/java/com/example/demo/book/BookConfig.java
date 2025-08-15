/*package com.example.demo.book;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class BookConfig {

    @Bean
    CommandLineRunner commandLineRunner(BookRepository bookRepository) {

        return args -> {
            Book book = new Book(
                    null, // ID is auto-generated
                    "Java Programming", // title
                    List.of("John Doe", "Jane Smith"), // authors (multiple authors)
                    "Tech Publisher", // publisher
                    "Programming", // category
                    "English", // language
                    2023, // publication year
                    "978-1234567890", // ISBN
                    "1st", // edition
                    "A comprehensive guide to Java programming", // summary
                    "http://example.com/image.jpg" // cover image URL
            );
            bookRepository.saveAll(List.of(book));
        };



    }
}
*/