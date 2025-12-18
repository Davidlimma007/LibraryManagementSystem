package com.library.management.service;


import com.library.management.exception.BookNotFoundException;
import com.library.management.model.Book;
import com.library.management.model.Borrowing;
import com.library.management.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service // Indica ao Spring que esta classe é um serviço
@RequiredArgsConstructor // Gera um construtor com argumentos para todos os campos finais
public class BookService {

    private final BookRepository bookRepository; // Repositório para operações de banco de dados relacionadas a livros

    //Create
    // Método para salvar um livro
    public Book saveBook(Book book) {
        return bookRepository.save(book); // Salva um livro no banco de dados
    }

    //Read
    // Método para encontrar um livro por ID
    public Book findBookById(Long id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id)); // Lança uma exceção se o livro não for encontrado
    }

    //Read
    // Método para retornar todos os livros
    public List<Book> findAllBooks(){
        return bookRepository.findAll(); // Retorna todos os livros do banco de dados
    }

    //Update
    // Método para atualizar um livro existente
    public Book updateBook(Long id, Book bookDetails){
        Book book = findBookById(id); // Encontra o livro pelo ID

        // Atualiza os detalhes do livro
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setGenre(bookDetails.getGenre());
        book.setYearPublished(bookDetails.getYearPublished());
        book.setNumberOfPages(bookDetails.getNumberOfPages());
        book.setTotalUnits(bookDetails.getTotalUnits());

        return bookRepository.save(book); // Salva as alterações no banco de dados
    }

    //Delete
    // Método para deletar um livro por ID
    public void deleteBook(Long id) {
        Book book = findBookById(id); // Encontra o livro pelo ID
        bookRepository.delete(book); // Deleta o livro do banco de dados
    }
}
