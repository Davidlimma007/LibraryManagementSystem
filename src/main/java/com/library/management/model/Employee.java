package com.library.management.model;


import com.library.management.enums.Position;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tb_employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends Person{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "position", nullable = false)
    private Position position;

    @Column(name = "admission_date", nullable = false)
    private LocalDate admissionDate;


}
