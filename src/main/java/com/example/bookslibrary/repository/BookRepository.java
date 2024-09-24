package com.example.bookslibrary.repository;

import com.example.bookslibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByAuthorIgnoreCaseAndNameIgnoreCase(String author, String name);
}
