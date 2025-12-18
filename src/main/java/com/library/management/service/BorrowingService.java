package com.library.management.service;

import com.library.management.enums.BorrowingsStatus;
import com.library.management.exception.*;
import com.library.management.model.Book;
import com.library.management.model.Borrowing;
import com.library.management.model.User;
import com.library.management.repository.BorrowingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class BorrowingService {

    private final BorrowingRepository borrowingRepository; // Repositório para gerenciar operações de empréstimo
    private final UserService userService; // Serviço para gerenciar operações relacionadas a usuários
    private final BookService bookService; // Serviço para gerenciar operações relacionadas a livros

    private final User user;
    private final Borrowing borrowing;

    private final double dailyFineRate = 2.50; // Multa diária por atraso na devolução
    private final double fixedFineFee = 50.0; // Taxa fixa de multa

    // Método para realizar o empréstimo de um livro por um usuário
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
                BorrowingsStatus.ACTIVE,
                false, // isRenewed inicialmente falso
                0.0 // currentFine inicialmente zero
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

    // Método para renovar o empréstimo de um livro
    public Borrowing renewBorrowing(Long borrowingId) {
        //1. BUSCAR e VALIDAR o EMPRÉSTIMO pelo ID
        Borrowing borrowing = borrowingRepository.findById(borrowingId) // Buscar empréstimo pelo ID
                .orElseThrow(() -> new BorrowingNotFoundException("Empréstimo ID " + borrowingId + " não encontrado."));

        // Verificar se o empréstimo está ativo
        if (borrowing.getStatus() != BorrowingsStatus.ACTIVE) {
            throw new BorrowingNotFoundException("Empréstimo ID " + borrowingId + " não está ativo e não pode ser renovado.");
        }

        // Verificar se o empréstimo está atrasado
        if(LocalDate.now().compareTo(borrowing.getDueDate()) > 0){
            throw new OverdueLoanCannotBeRenewedException("Empréstimo ID " + borrowingId + " está atrasado e não pode ser renovado.");
        }

        // Verificar se o empréstimo já foi renovado
        if(borrowing.getIsRenewed()){
            throw new RenewingLimitExceededException("Emprestimo ID " + borrowingId + " já foi renovado e atingiu o limite de renovações.");
        }

        //2. ATUALIZAR a data de devolução do EMPRÉSTIMO
        LocalDate newDueDate = borrowing.getDueDate().plusDays(7); // Nova data de devolução: data atual + 7 dias
        borrowing.setDueDate(newDueDate); // Atualizar a data de devolução

        borrowing.setIsRenewed(true); // Marcar o empréstimo como renovado

        Borrowing updatedBorrowing = borrowingRepository.save(borrowing); // Salvar as alterações do empréstimo

        return updatedBorrowing;
    }

    // Método para realizar a devolução de um livro emprestado
    public Borrowing returnBook(Long borrowingId) {
        //1. BUSCAR e VALIDAR o EMPRÉSTIMO pelo ID
        Borrowing borrowing = borrowingRepository.findById(borrowingId) // Buscar empréstimo pelo ID
                .orElseThrow(() -> new BorrowingNotFoundException("Empréstimo ID " + borrowingId + " não encontrado."));

        // Verificar se o empréstimo está ativo
        if (borrowing.getStatus() != BorrowingsStatus.ACTIVE) {
            throw new BorrowingNotFoundException("Empréstimo ID " + borrowingId + " não está ativo e não pode ser devolvido.");
        }

        //2. REGISTRO DEVOLUÇÃO
        borrowing.setReturnDate(LocalDate.now()); // Data de devolução: data atual

        //3.CALCULAR MULTA se houver atraso na devolução
        long daysOverdue = ChronoUnit.DAYS.between(borrowing.getDueDate(), borrowing.getReturnDate());

        if(daysOverdue > 0){
            double finePerDay = daysOverdue * dailyFineRate; // Cálculo da multa por dia de atraso
            double fine = finePerDay + fixedFineFee; // Cálculo da multa total
            borrowing.setCurrentFine(fine); // Atualizar a multa atual do empréstimo
            user.setIsBlacklisted(true); // Atualiza o nome do user na lista negra
            user.setFineHistory(user.getFineHistory() + fine); // Atualiza o histórico de multas
            userService.saveUser(user);
        }

        //4. ATUALIZAR o registro de EMPRÉSTIMO
        borrowing.setStatus(BorrowingsStatus.RETURNED); // Atualizar status para DEVOLVIDO
        Borrowing updatedBorrowing = borrowingRepository.save(borrowing); // Salvar as alterações do empréstimo

        //5. ATUALIZAR contadores
        //5.1 Atualizar contador de livros emprestados do usuário
        User user = borrowing.getUser();
        user.setBooksBorrowedCount(user.getBooksBorrowedCount() - 1);
        userService.saveUser(user); // Salvar as alterações do usuário

        //5.2 Atualizar contador de unidades emprestadas do livro
        Book book = borrowing.getBook();
        book.setTotalUnitsborrowed(book.getTotalUnitsborrowed() - 1);
        bookService.saveBook(book); // Salvar as alterações do livro

        return updatedBorrowing;
    }

}
