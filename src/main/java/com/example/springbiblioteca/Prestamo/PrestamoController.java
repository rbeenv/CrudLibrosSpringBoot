package com.example.springbiblioteca.Prestamo;

import com.example.springbiblioteca.Libro.Libro;
import com.example.springbiblioteca.Libro.LibroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/prestamo")

public class PrestamoController {
    PrestamoRepository repositorioPrestamos;

    public PrestamoController(){
    }

    @Autowired
    public PrestamoController(PrestamoRepository repositorioPrestamos){this.repositorioPrestamos = repositorioPrestamos;}

    //GET --> SELECT *
    @GetMapping
    public ResponseEntity<List<Prestamo>> getPrestamo(){
        List<Prestamo> lista = this.repositorioPrestamos.findAll();
        System.out.println(lista);
        return ResponseEntity.ok(lista);
    }

    //GET BY ISBN --> SELECT BY ISBN
    @GetMapping("/{id}")
    @Cacheable
    public ResponseEntity<Prestamo> getPrestamoJson(@PathVariable int id){
        Prestamo p = this.repositorioPrestamos.findById(id).get();
        return ResponseEntity.ok(p);
    }

    //POST --> INSERT
    @PostMapping("/prestamo")
    public ResponseEntity<Prestamo> addPrestamo(@Valid @RequestBody Prestamo prestamo){
        System.out.println("Entra aqui");
        Prestamo prestamoPersistido = this.repositorioPrestamos.save(prestamo);
        return ResponseEntity.ok().body(prestamoPersistido);
    }

    //POST con Form normal, se trabajará con JSONs normalmente...
    @PostMapping(value = "/prestamoForm", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Prestamo> addPrestamoForm(@RequestParam int id, @RequestParam int idEjemplar, @RequestParam int idUsuario,
                                                 @RequestParam LocalDate fechaInicio, @RequestParam LocalDate fechaFin){
        Prestamo p = new Prestamo();
        p.setId(id);
        p.setIdEjemplar(idEjemplar);
        p.setIdUsuario(idUsuario);
        p.setFechaInicio(fechaInicio);
        p.setFechaFin(fechaFin);
        this.repositorioPrestamos.save(p);
        return ResponseEntity.created(null).body(p);
    }

    //PUT --> UPDATE
    //falta actualizar ficheros
    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> updatePrestamo(@RequestBody Prestamo prestamo, @PathVariable int id){
        Prestamo prestamoPersistido = repositorioPrestamos.save(prestamo);
        return ResponseEntity.ok().body(prestamoPersistido);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePrestamo(@PathVariable int id){
        repositorioPrestamos.deleteById(id);
        String mensaje = "libro con isbn: "+id+" borrado";
        return ResponseEntity.ok().body(mensaje);
    }
}
