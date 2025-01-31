package com.example.springbiblioteca.Prestamo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
//Anotaciones Lombok
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Prestamo {
    @Id
    private int id;
    private int idEjemplar;
    private int idUsuario;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
