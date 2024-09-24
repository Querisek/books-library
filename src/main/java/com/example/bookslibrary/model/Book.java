package com.example.bookslibrary.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "books", uniqueConstraints =
@UniqueConstraint(columnNames = {"author", "name"}))
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @NotBlank
    @Column(name = "author")
    private String author;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotNull
    @Range(min = 1, max = 30000)
    @Column(name = "pages")
    private Integer pages;

    @NotNull
    @Range(min = 0, max = 10000)
    @Column(name = "quantity")
    private Integer quantity;

    public Book() {

    }

    public Book(String author, String name, Integer pages) {
        this.author = author;
        this.name = name;
        this.pages = pages;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public @NotEmpty String getAuthor() {
        return author;
    }

    public void setAuthor(@NotEmpty String author) {
        this.author = author;
    }

    public @NotEmpty String getName() {
        return name;
    }

    public void setName(@NotEmpty String name) {
        this.name = name;
    }

    public @NotNull @Range(min = 1, max = 30000) Integer getPages() {
        return pages;
    }

    public void setPages(@NotNull @Range(min = 1, max = 30000) Integer pages) {
        this.pages = pages;
    }

    public @NotNull @Range(min = 0, max = 10000) Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(@NotNull @Range(min = 0, max = 10000) Integer quantity) {
        this.quantity = quantity;
    }
}
