package com.book.libary.repository;

import com.book.libary.model.entity.Book;
import com.book.libary.model.enums.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByBookStatus(BookStatus bookStatus);

    List<Book> findByTitle(String title);
    Optional<Book> findById(Long id);
}
