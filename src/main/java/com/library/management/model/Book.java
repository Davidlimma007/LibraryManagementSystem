package com.library.management.model;


import com.library.management.enums.BookGenre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_book")
@Data // Gera getters e setters automaticamente
@NoArgsConstructor // Gera um construtor sem argumentos
@AllArgsConstructor // Gera um construtor com todos os argumentos
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private int yearPublished;
    private int numberOfPages;

    @Enumerated(EnumType.STRING) // Armazena o enum como String no banco de dados
    private BookGenre genre;

    private int totalUnits;
    private int totalUnitsborrowed;
}
