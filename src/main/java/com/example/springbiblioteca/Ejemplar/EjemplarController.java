package com.example.springbiblioteca.Ejemplar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/Ejemplar")
public class EjemplarController {
    EjemplarRepository ejemplarRepository;

    public EjemplarController(){

    }

    @Autowired
    public EjemplarController(EjemplarRepository ejemplarRepository){
        this.ejemplarRepository = ejemplarRepository;
    }

    //Get --> Select *
    @GetMapping
    public ResponseEntity<List<Ejemplar>> getEjemplar(){
        List<Ejemplar> lista = this.ejemplarRepository.findAll();
        System.out.println(lista);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    @Cacheable
    public ResponseEntity<Ejemplar> getEjemplarJson(@PathVariable int id){
        Ejemplar e = this.ejemplarRepository.findById(id).get();
        return ResponseEntity.ok(e);
    }

    @PostMapping("/Ejemplar")
    public ResponseEntity<Ejemplar> addEjemplar(@RequestBody Ejemplar ejemplar){
        System.out.println("Entra aqui");
        Ejemplar ejemplarPersistido = this.ejemplarRepository.save(ejemplar);
        return ResponseEntity.ok().body(ejemplarPersistido);
    }

    @PostMapping(value = "/EjemplarForm", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Ejemplar> addEjemplarForm(@RequestParam int id,
                                                   @RequestParam String isbn,
                                                   @RequestParam String estado){
        Ejemplar ejemplar = new Ejemplar();
        ejemplar.setId(id);
        ejemplar.setIsbn(isbn);
        ejemplar.setEstado(estado);
        Ejemplar ejemplarPersistido = this.ejemplarRepository.save(ejemplar);
        return ResponseEntity.ok().body(ejemplarPersistido);
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<Ejemplar> updateEjemplar(@PathVariable int id, @RequestBody Ejemplar ejemplar){
        Ejemplar ejemplarPersistido = this.ejemplarRepository.save(ejemplar);
        return ResponseEntity.ok().body(ejemplarPersistido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ejemplar> deleteEjemplar(@PathVariable int id){
        this.ejemplarRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
