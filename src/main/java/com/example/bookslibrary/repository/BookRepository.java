package com.example.bookslibrary.repository;

import com.example.bookslibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByAuthorIgnoreCaseAndNameIgnoreCase(String author, String name);
    List<Book> findAllByOrderByName();
    Book findByAuthorIgnoreCaseAndNameIgnoreCase(String author, String name);
}
