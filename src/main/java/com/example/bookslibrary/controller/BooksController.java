package com.example.bookslibrary.controller;

import com.example.bookslibrary.dto.BookDto;
import com.example.bookslibrary.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String home(Model model) {
        List<BookDto> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/add")
    public String addNewBook() {
        return "newbook";
    }

    @GetMapping("/borrowed")
    public String listBorrowedBooks() {
        return "borrowedbooks";
    }
}
