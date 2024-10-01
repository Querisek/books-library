package com.example.bookslibrary.service;

import com.example.bookslibrary.dto.BookDto;
import com.example.bookslibrary.dto.BorrowedBookDto;
import com.example.bookslibrary.dto.UserDto;
import com.example.bookslibrary.model.BorrowedBook;
import com.example.bookslibrary.repository.BorrowedBookRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowedBookService {

    private BorrowedBookRepository borrowedBookRepository;
    private BookService bookService;
    private UserService userService;
    private ModelMapper modelMapper;

    public BorrowedBookService(BorrowedBookRepository borrowedBookRepository, BookService bookService, UserService userService, ModelMapper modelMapper) {
        this.borrowedBookRepository = borrowedBookRepository;
        this.bookService = bookService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public void borrowBook(BookDto book, UserDto user) {
        BorrowedBookDto borrowedBook = new BorrowedBookDto();
        book.setQuantity(book.getQuantity() - 1);
        borrowedBook.setBook(book);
        borrowedBook.setUser(user);
        bookService.addNewBook(book);
        borrowedBookRepository.save(modelMapper.map(borrowedBook, BorrowedBook.class));
    }

    public List<BorrowedBookDto> findBorrowedBooksByUser(String email) {
        return borrowedBookRepository.findAllByUserEmail(email).stream()
                .map(book -> modelMapper.map(book, BorrowedBookDto.class))
                .collect(Collectors.toList());
    }

    public BorrowedBookDto findById(Long id) {
        return modelMapper.map(borrowedBookRepository.findById(id), BorrowedBookDto.class);
    }

    public List<BorrowedBookDto> findAll() {
        return borrowedBookRepository.findAll().stream()
                .map(book -> modelMapper.map(book, BorrowedBookDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public void returnToLibrary(BorrowedBookDto borrowedBookDto) {
        BookDto book = borrowedBookDto.getBook();
        book.setQuantity(book.getQuantity() + 1);
        bookService.addNewBook(book);
        borrowedBookRepository.delete(modelMapper.map(borrowedBookDto, BorrowedBook.class));
    }

    public List<BorrowedBookDto> findAllByName() {
        return borrowedBookRepository.findAllByOrderByBookName().stream()
                .map(book -> modelMapper.map(book, BorrowedBookDto.class))
                .collect(Collectors.toList());
    }
}
