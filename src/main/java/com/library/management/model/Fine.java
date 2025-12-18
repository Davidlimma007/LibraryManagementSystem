package com.library.management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tb_fine")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double value; // Para registrar o valor da multa

    @Column(nullable = false)
    private boolean isPaid; // Para registrar se foi quitada

    @Column(nullable = false)
    private LocalDate calculationDate; // Para registrar quando foi calculada

    @Column(nullable = false)
    private LocalDate paymentDate; // Para registrar quando foi quitada

    @ManyToOne
    @JoinColumn(name = "borrowing_id", nullable = false)
    Borrowing borrowing; // Relacionamento muitos para 1 (1 empréstimo pode ter muitas multas)

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user; // Relacionamento muitos para 1 (1 usuário pode ter muitas multas)

}
