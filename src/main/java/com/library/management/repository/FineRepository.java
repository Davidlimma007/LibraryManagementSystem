package com.library.management.repository;

import com.library.management.model.Fine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FineRepository extends JpaRepository<Fine, Long> {
    // O Spring Data JPA cria automaticamente métodos como:
    // - save(Fine)
    // - findById(Long)
    // - findAll()
    // - delete(Fine)
}
