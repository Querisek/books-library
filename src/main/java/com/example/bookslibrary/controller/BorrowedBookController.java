package com.example.bookslibrary.controller;

import com.example.bookslibrary.dto.BookDto;
import com.example.bookslibrary.dto.BorrowedBookDto;
import com.example.bookslibrary.dto.UserDto;
import com.example.bookslibrary.service.BookService;
import com.example.bookslibrary.service.BorrowedBookService;
import com.example.bookslibrary.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BorrowedBookController {

    private final BorrowedBookService borrowedBookService;
    private final BookService bookService;
    private final UserService userService;

    public BorrowedBookController(BorrowedBookService borrowedBookService, BookService bookService, UserService userService) {
        this.borrowedBookService = borrowedBookService;
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping("/books/borrow")
    public String borrowBook(@RequestParam("id") Long id) {
        BookDto book = bookService.findBookById(id);
        if(book == null) {
            return "redirect:/books?book-not-found";
        }
        if(book.getQuantity() == 0) {
            return "redirect:/books?book-not-available";
        } else {
            UserDto user = userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
            List<BorrowedBookDto> books = borrowedBookService.findBorrowedBooksByUser(user.getEmail());
            for(BorrowedBookDto bookDto : books) {
                if(bookDto.getBook().getId().equals(id)) {
                    return "redirect:/books?book-already-borrowed";
                }
            }
            borrowedBookService.borrowBook(book, user);
            return "redirect:/books?book-successfully-borrowed";
        }
    }

    @GetMapping("/books/borrowed")
    public String listBorrowedBooks(Model model) {
        model.addAttribute("borrowedBooks", borrowedBookService.findBorrowedBooksByUser(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "borrowedbooks";
    }

    @GetMapping("/books/borrowed/return")
    public String returnBorrowedBook(@RequestParam("id") Long id) {
        if(borrowedBookService.findById(id) == null) {
            return "redirect:/books/borrowed?not-found";
        }
        if(borrowedBookService.findById(id).getUser().getEmail().equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
            borrowedBookService.returnToLibrary(borrowedBookService.findById(id));
            return "redirect:/books/borrowed?succesfully-returned";
        }
        return "redirect:/books/borrowed?not-found";
    }

    @GetMapping("/books/delete")
    public String deleteBook(@RequestParam("id") Long id) {
        if(bookService.findBookById(id) == null) {
            return "redirect:/books?not-found";
        }
        List<BorrowedBookDto> borrowedBooks = borrowedBookService.findAll();
        for(BorrowedBookDto borrowedBook : borrowedBooks) {
            if(borrowedBook.getBook().getId().equals(id)) {
                return "redirect:/books?books-not-returned";
            }
        }
        bookService.deleteBookById(id);
        return "redirect:/books?book-successfully-deleted";
    }

    @GetMapping("/books/borrowed/all")
    public String listAllBorrowedBooks(Model model) {
        model.addAttribute("borrowedBooks", borrowedBookService.findAllByName());
        return "borrowed-books-all";
    }
}
