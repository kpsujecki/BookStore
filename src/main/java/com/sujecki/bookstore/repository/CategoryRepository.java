package com.sujecki.bookstore.repository;

import com.sujecki.bookstore.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category getReferenceById(Long id);
}
