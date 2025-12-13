package com.library.management.repository;

import com.library.management.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // O Spring Data JPA cria automaticamente métodos como:
    // - save(Employee)
    // - findById(Long)
    // - findAll()
    // - delete(Employee)
}
