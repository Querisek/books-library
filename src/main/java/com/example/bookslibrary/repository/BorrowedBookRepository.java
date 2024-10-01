package com.example.bookslibrary.repository;

import com.example.bookslibrary.model.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Long> {
    List<BorrowedBook> findAllByUserUsername(String username);
    List<BorrowedBook> findAllByUserEmail(String email);
    List<BorrowedBook> findAllByOrderByBookName();
}
