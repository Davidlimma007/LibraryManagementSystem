package com.library.management.repository;

import com.library.management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // O Spring Data JPA cria automaticamente métodos como:
    // - save(User)
    // - findById(Long)
    // - findAll()
    // - delete(User)
}
