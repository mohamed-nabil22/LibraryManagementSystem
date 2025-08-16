package com.example.demo.Book;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @JsonIgnore
    @ManyToMany(
            cascade = { CascadeType.PERSIST, CascadeType.MERGE }
    )
    @JoinTable(name = "book_categories",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;
    private String language;
    private int publicationYear;
    @Column(length = 20, unique = true)
    private String isbn;
    private String edition;

    @Column(length = 2000)
    private String summary;

    private String coverImageUrl;

    @JsonIgnore
    @ManyToOne(
            fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }
    )
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @JsonIgnore
    @ManyToMany(
            cascade = { CascadeType.PERSIST, CascadeType.MERGE }
    )
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> bookAuthors;

    int availableQuantity;

    public void addAuthor(Author a) {
        bookAuthors.add(a); // position determines author_order
        a.getBooks().add(this);
    }
    public void removeAuthor(Author a) {
        bookAuthors.remove(a);
        a.getBooks().remove(this);
    }
    public void addCategory(Category c) {
        categories.add(c);
        c.getBooks().add(this);
    }
    public void removeCategory(Category c) {
        categories.remove(c);
        c.getBooks().remove(this);
    }

}

