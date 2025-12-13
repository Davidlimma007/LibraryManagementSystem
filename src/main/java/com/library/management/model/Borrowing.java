package com.library.management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tb_borrowing")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Relacionamento onde o usuário pode ter vários empréstimos
    @ManyToOne   // Muitos empréstimos para um usuário
    private User user;

    //Relacionamento onde o livro pode ter vários empréstimos
    @ManyToOne
    private Book book;

    //Data de empréstimo do livro
    @Column (nullable = false)
    private LocalDate borrowDate;

    //Data de devolução do livro
    @Column (nullable = false)
    private LocalDate dueDate;

    //Data de devolução efetiva do livro
    @Column (nullable = true)
    private LocalDate returnDate;
}
