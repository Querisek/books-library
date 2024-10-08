package com.example.bookslibrary.service;

import com.example.bookslibrary.dto.BookDto;
import com.example.bookslibrary.model.Book;
import com.example.bookslibrary.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public BookService(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    public List<BookDto> getAllBooks() {
        return bookRepository.findAllByOrderByName().stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

    public void addNewBook(BookDto bookDto) {
        bookRepository.save(modelMapper.map(bookDto, Book.class));
    }

    public boolean existsByAuthorAndName(String author, String name) {
        return bookRepository.existsByAuthorIgnoreCaseAndNameIgnoreCase(author, name);
    }

    public BookDto findBookById(Long id) {
        return modelMapper.map(bookRepository.findById(id), BookDto.class);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    public BookDto findBookByAuthorAndName(String author, String name) {
        return modelMapper.map(bookRepository.findByAuthorIgnoreCaseAndNameIgnoreCase(author, name), BookDto.class);
    }
}
