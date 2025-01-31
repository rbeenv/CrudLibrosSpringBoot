package com.example.springbiblioteca.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

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
public class Usuario {
    @Id
    @NotNull
    @Pattern(regexp = "^\\d{8}[A-HJ-NP-TV-Z]$")
    private int id;
    @Size(max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private String nombre;
    private String apellido;
    @Pattern(regexp = "^[A-Za-z0-9]{1,50}@gmail\\.com$")
    private String email;
    @Length(min = 4, max = 12)
    private String password;
    @Pattern(regexp = "^(normal|administrador)$")
    private String tipo;
    private LocalDate penalizacionHasta;
}