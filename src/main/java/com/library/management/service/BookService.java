package com.library.management.service;


import com.library.management.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service // Indica ao Spring que esta classe é um serviço
@RequiredArgsConstructor // Gera um construtor com argumentos para todos os campos finais
public class BookService {

    private final BookRepository bookRepository; // Repositório para operações de banco de dados relacionadas a livros
    
}
