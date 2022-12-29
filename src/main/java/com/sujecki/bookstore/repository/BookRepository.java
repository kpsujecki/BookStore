package com.sujecki.bookstore.repository;

import com.sujecki.bookstore.model.Book;
import com.sujecki.bookstore.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Override
    Optional<Book> findById(Long aLong);

    List<Book> findAllByCategory(Category category);

    List<Book> findAllByAuthor(String author);
}
