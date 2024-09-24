package com.example.bookslibrary.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class BorrowedBookDto {

    @NotNull
    private Long id;

    @NotEmpty
    private UserDto user;

    @NotEmpty
    private BookDto book;

    public BorrowedBookDto() {

    }

    public @NotNull Long getId() {
        return id;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }

    public @NotEmpty UserDto getUser() {
        return user;
    }

    public void setUser(@NotEmpty UserDto user) {
        this.user = user;
    }

    public @NotEmpty BookDto getBook() {
        return book;
    }

    public void setBook(@NotEmpty BookDto book) {
        this.book = book;
    }
}
