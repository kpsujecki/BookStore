package com.sujecki.bookstore.repository;

import com.sujecki.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Override
    Optional<Book> findById(Long aLong);
}
