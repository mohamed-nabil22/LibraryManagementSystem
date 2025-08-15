package com.example.demo.book;
import java.util.List;
import jakarta.annotation.*;
import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public Book(Long id, String title, List<String> authors, String publisher, String category, String language, int publicationYear, String isbn, String edition, String summary, String coverImageUrl) {
        setId(id);
        setTitle(title);
        setAuthors(authors);
        setPublisher(publisher);
        setCategory(category);
        setLanguage(language);
        setPublicationYear(publicationYear);
        setIsbn(isbn);
        setEdition(edition);
        setSummary(summary);
        setCoverImageUrl(coverImageUrl);
    }

    public Book() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }
}

