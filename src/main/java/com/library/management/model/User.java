package com.library.management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "tb_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Para controlar se o usuário está na lista negra
    @Column(name = "is_blacklisted", nullable = false)
    private Boolean isBlacklisted;

    //Para controlar o total de livros emprestados pelo usuário
    @Column(name = "books_borrowed_count", nullable = false)
    private int booksBorrowedCount;

    //Para controlar o total de multas pagas pelo usuário
    @Column(name = "total_fines_paid", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalFinesPaid;

    @OneToMany(mappedBy = "user")
    private List<Borrowing> borrowings;
}
