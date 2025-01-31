package com.example.springbiblioteca.Ejemplar;

import jakarta.persistence.*;

//Anotaciones Lombok
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ejemplar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 20)
    private int id;

    @NotBlank(message = "El campo ISBN no puede estar vacio")
    @Column (name = "isbn", nullable = false, length = 9)
    @Size(max = 9, message = "EL campo isbn tendrá como máximo 9 caracteres")
    @Pattern(regexp = "[A-Z]{4}", message = "ISBN con 4 mayusculas")
    @NotNull(message = "El campo ISBN no puede estar vacio")
    private String isbn;

    @Column (name = "estado", nullable = false, length = 100)
    @Size(max = 100, message = "EL campo estado tendrá como máximo 100 caracteres")
    @Pattern(regexp = "^(Prestado|Disponible|Dañado)$", message = "Introduce un estado válido")
    private String estado;
}
