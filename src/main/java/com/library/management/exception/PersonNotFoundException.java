package com.library.management.exception;

// Exceção personalizada para indicar que uma pessoa não foi encontrada, ela é do tipo não verificada (unchecked)
public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(Long id) {
        super(" Person with ID " + id + " not found."); // Mensagem de erro personalizada
    }
}
