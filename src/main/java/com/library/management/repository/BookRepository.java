package com.library.management.repository;

import com.library.management.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Indica que esta interface é um repositório Spring Data JPA
public interface BookRepository extends JpaRepository<Book, Long> {
    // O Spring Data JPA cria automaticamente métodos como:
    // - save(Book)
    // - findById(Long)
    // - findAll()
    // - delete(Book)
}
