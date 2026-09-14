package fi.haagahelia.bookstorepalinaleksi.domain;

import java.time.Year;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class Book {

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    

    private String title;
    private String author;
    private Year publicationYear;
    private String isbn;
    private double price;

    public Book(){}
    
    public Book(String title, String author, Year publicationYear, String isbn, double price) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public Year getPublicationYear() {
        return publicationYear;
    }
    public void setPublicationYear(Year publicationYear) {
        this.publicationYear = publicationYear;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Book{");
        sb.append("id=").append(id);
        sb.append("title=").append(title);
        sb.append(", author=").append(author);
        sb.append(", publicationYear=").append(publicationYear);
        sb.append(", isbn=").append(isbn);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }


    
}   
