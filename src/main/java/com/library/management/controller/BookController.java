package com.library.management.controller;

import com.library.management.model.Book;
import com.library.management.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indica ao Spring que esta classe é um controlador REST
@RequestMapping("/api/v1/books") // Mapeia as requisições para /api/v1/books
@RequiredArgsConstructor // Gera um construtor com argumentos para todos os campos finais
public class BookController {

    private final BookService bookService; // Serviço para operações relacionadas a livros

    // Create
    // Endpoint para criar um novo livro (POST)
    @PostMapping // Mapeia requisições POST para este método
    public Book createBook(@RequestBody Book book){
        // @RequestBody indica que o corpo da requisição será mapeado para o objeto Book
        return bookService.saveBook(book); // Chama o serviço para salvar um livro
    }

    //Read All
    // Endpoint para obter todos os livros (GET)
    @GetMapping // Mapeia para Get /api/v1/books
    public List<Book> getAllBooks(){
        return bookService.findAllBooks(); // Chama o serviço para obter todos os livros
    }

    //Read by ID
    // Endpoint para obter um livro por ID (GET)
    @GetMapping("/{id}") // Mapeia requisições GET para /api/v1/books/{id}
    public Book getBookById(@PathVariable Long id){
        // @PathVariable indica que o ID será extraído da URL
        return bookService.findBookById(id); // Chama o serviço para encontrar um livro pelo ID
    }

    //Update
    // Endpoint para atualizar um livro existente (PUT)
    @PutMapping("/{id}") // Mapeia requisições PUT para /api/v1/books/{id}
    public Book updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        // @PathVariable para o ID e @RequestBody para os detalhes do livro
        return bookService.updateBook(id, bookDetails); // Chama o serviço para atualizar o livro
    }

    // Delete
    // Endpoint para deletar um livro por ID (DELETE)
    @DeleteMapping("/{id}") // Mapeia requisições DELETE para /api/v1/books/{id}
    public void deleteBook(@PathVariable Long id) {
        // @PathVariable indica que o ID será extraído da URL
        bookService.deleteBook(id); // Chama o serviço para deletar o livro
    }
}
