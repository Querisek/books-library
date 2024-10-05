package com.example.bookslibrary.controller;

import com.example.bookslibrary.dto.BookDto;
import com.example.bookslibrary.service.BookService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public String addNewBook(Model model) {
        model.addAttribute("book", new BookDto());
        return "book-new";
    }

    @PostMapping("/saveNewBook")
    public String saveNewBook(@Valid @ModelAttribute("book") BookDto book, BindingResult result) {
        if(bookService.existsByAuthorAndName(book.getAuthor(), book.getName())) {
            result.rejectValue("author", "error.author", "Book with this author for the given book already exists.");
            result.rejectValue("name", "error.name", "Book with provided name and author already exists.");
        }
        if(result.hasErrors()) {
            return "book-new";
        }
        bookService.addNewBook(book);
        return "redirect:/books?newBook";
    }

    @GetMapping("/edit")
    public String editBook(@RequestParam("id") Long id, Model model) {
        model.addAttribute("book", bookService.findBookById(id));
        return "book-edit";
    }

    @PostMapping("/edit")
    public String changeBookData(@Valid @ModelAttribute("book") BookDto book, BindingResult result) {
        if(bookService.existsByAuthorAndName(book.getAuthor(), book.getName()) && (!book.getId().equals(bookService.findBookByAuthorAndName(book.getAuthor(), book.getName()).getId()))) {
            result.rejectValue("author", "error.author", "Book with this author for the given book already exists.");
            result.rejectValue("name", "error.name", "Book with provided name and author already exists.");
        }
        if(result.hasErrors()) {
            return "book-edit";
        }
        bookService.addNewBook(book);
        return "redirect:/books?successfully-edited";
    }
}
