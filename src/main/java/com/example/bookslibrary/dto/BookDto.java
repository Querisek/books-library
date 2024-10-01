package com.example.bookslibrary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public class BookDto {

    private Long id;

    @NotBlank(message = "Enter author name.")
    private String author;

    @NotBlank(message = "Enter title.")
    private String name;

    @NotNull(message = "Enter amount of pages.")
    @Range(min = 1, max = 30000)
    private Integer pages;

    @NotNull(message = "Enter quantity.")
    @Range(min = 0, max = 10000)
    private Integer quantity;

    public BookDto() {

    }

    public BookDto(String author, String name, Integer pages) {
        this.author = author;
        this.name = name;
        this.pages = pages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Enter author name.") String getAuthor() {
        return author;
    }

    public void setAuthor(@NotBlank(message = "Enter author name.") String author) {
        this.author = author;
    }

    public @NotBlank(message = "Enter title.") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Enter title.") String name) {
        this.name = name;
    }

    public @NotNull(message = "Enter amount of pages.") @Range(min = 1, max = 30000) Integer getPages() {
        return pages;
    }

    public void setPages(@NotNull(message = "Enter amount of pages.") @Range(min = 1, max = 30000) Integer pages) {
        this.pages = pages;
    }

    public @NotNull(message = "Enter quantity.") @Range(min = 0, max = 10000) Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(@NotNull(message = "Enter quantity.") @Range(min = 0, max = 10000) Integer quantity) {
        this.quantity = quantity;
    }
}
