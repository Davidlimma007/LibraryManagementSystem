package com.library.management.service;

import com.library.management.enums.BorrowingsStatus;
import com.library.management.exception.BlackListedUserException;
import com.library.management.exception.BookNotAvailableException;
import com.library.management.exception.LoanLimitExceededException;
import com.library.management.model.Book;
import com.library.management.model.Borrowing;
import com.library.management.model.User;
import com.library.management.repository.BorrowingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BorrowingService {

    private final BorrowingRepository borrowingRepository; // Repositório para gerenciar operações de empréstimo
    private final UserService userService; // Serviço para gerenciar operações relacionadas a usuários
    private final BookService bookService; // Serviço para gerenciar operações relacionadas a livros

    public Borrowing borrowBook(Long userId, Long bookId) {
        //1. BUSCAR e VALIDAR o USUÁRIO pelo ID
        User user = userService.findUserById(userId);

        if (user.getIsBlacklisted()){
            throw new BlackListedUserException("Usuário ID " + userId + " está bloqueado e não pode realizar empréstimos.");
        }

        if(user.getBooksBorrowedCount() >= 3){
            throw new LoanLimitExceededException("Usuário ID " + userId + " atingiu o limite máximo de empréstimos.");
        }

        //2. BUSCAR e VALIDAR o LIVRO pelo ID
        Book book = bookService.findBookById(bookId);

        int disponibleBook = book.getTotalUnits() - book.getTotalUnitsborrowed();
        if(disponibleBook <= 0){
            throw new BookNotAvailableException("Livro ID " + bookId + " não está disponível para empréstimo.");
        }

        //3. CRIAR registro de EMPRÉSTIMO

        //3.1 Data de empréstimo: data atual
        LocalDate borrowDate = LocalDate.now();

        //3.2 Data de devolução: data atual + 7 dias
        LocalDate dueDate = borrowDate.plusDays(7);

        //3.3 Registro do empréstimoorrowing
        Borrowing newBorrowing = new Borrowing(
                null, // id será gerado automaticamente
                user,
                book,
                borrowDate,
                dueDate,
                null, // returnDate inicialmente nulo
                BorrowingsStatus.ACTIVE
        );

        Borrowing savedBorrowing = borrowingRepository.save(newBorrowing);

        //4. ATUALIZAR contadores
        //4.1 Atualizar contador de livros emprestados do usuário
        user.setBooksBorrowedCount(user.getBooksBorrowedCount() + 1);
        userService.saveUser(user); // Salvar as alterações do usuário

        //4.2 Atualizar contador de unidades emprestadas do livro
        book.setTotalUnitsborrowed(book.getTotalUnitsborrowed() +1);
        bookService.saveBook(book); // Salvar as alterações do livro

        return savedBorrowing;
    }

}
