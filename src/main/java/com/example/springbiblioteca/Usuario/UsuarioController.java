package com.example.springbiblioteca.Usuario;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    UsuarioRepository repositorioUsuario;

    public UsuarioController(){}

    @Autowired
    public UsuarioController(UsuarioRepository repositorioUsuario) {this.repositorioUsuario = repositorioUsuario;}

    @GetMapping
    public List<Usuario> getUsuario(){
        List<Usuario> lista = this.repositorioUsuario.findAll();
        System.out.println(lista);
        return lista;
    }

    @GetMapping("/{id}")
    @Cacheable
    public Usuario getUsuarioJson(@PathVariable int id){
        Usuario u = this.repositorioUsuario.findById(id).get();
        return u;
    }

    @PostMapping("/usuario")
    public Usuario addUsuario(@Valid @RequestBody Usuario usuario){
        System.out.println("Entra aqui");
        Usuario usuarioPersistido = this.repositorioUsuario.save(usuario);
        return usuarioPersistido;
    }

    @PostMapping(value ="/usuarioForm", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Usuario addUsuarioForm(@PathVariable int id, @PathVariable String nombre, @PathVariable String apellido, @PathVariable String email,
                                  @PathVariable String password, @PathVariable String tipo, @PathVariable LocalDate penalizacionHasta){
        Usuario u = new Usuario();
        u.setId(id);
        u.setNombre(nombre);
        u.setApellido(apellido);
        u.setEmail(email);
        u.setPassword(password);
        u.setTipo(tipo);
        u.setPenalizacionHasta(penalizacionHasta);
        Usuario usuarioPersistido = this.repositorioUsuario.save(u);
        return usuarioPersistido;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario, @PathVariable int id){
        Usuario usuarioPersistido = this.repositorioUsuario.findById(id).get();
        return ResponseEntity.ok().body(usuarioPersistido);
    }

}
