package com.example.demo.book;
import java.util.List;
import jakarta.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;


    private List<String> authors;  // multiple authors

    private String publisher;
    private String category;
    private String language;
    private int publicationYear;
    private String isbn;
    private String edition;

    @Column(length = 2000)
    private String summary;

    private String coverImageUrl;

    int availableQuantity;

}

