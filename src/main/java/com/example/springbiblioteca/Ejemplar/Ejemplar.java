package com.example.springbiblioteca.Ejemplar;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Ejemplar {
    @Id
    private int id;
    private String isbn;
    private String estado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
