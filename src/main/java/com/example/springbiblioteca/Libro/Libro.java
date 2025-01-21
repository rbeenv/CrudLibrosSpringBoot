package com.example.springbiblioteca.Libro;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Libro {
    @Id
    private String isbn;
    private String titulo;
    private String autor;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
