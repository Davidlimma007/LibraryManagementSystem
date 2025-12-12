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

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "year_published", nullable = false)
    private int yearPublished;

    @Column(name = "number_of_pages", nullable = false)
    private int numberOfPages;

    @Column(name = "genre", nullable = false)
    @Enumerated(EnumType.STRING) // Armazena o enum como String no banco de dados
    private BookGenre genre;

    @Column(name = "total_units", nullable = false)
    private int totalUnits;

    @Column(name = "total_units_borrowed", nullable = false)
    private int totalUnitsborrowed;
}
