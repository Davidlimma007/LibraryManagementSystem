package com.library.management.exception;

// Exceção personalizada para indicar que um livro não foi encontrado, ela é do tipo não verificada (unchecked)
public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) {
        super("Book with ID " + id + " not found."); // Mensagem de erro personalizada
    }
}
