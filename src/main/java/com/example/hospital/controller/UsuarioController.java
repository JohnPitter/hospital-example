package com.example.hospital.controller;

import com.example.hospital.request.UsuarioRequest;
import com.example.hospital.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity getUsuarios() {
        return this.usuarioService.verUsuarios();
    }

    @PostMapping
    public ResponseEntity createUsuario(@Valid @RequestBody UsuarioRequest usuario) {
        return this.usuarioService.criarUsuario(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUsuario(@PathVariable("id") Long idUsuario, @Valid @RequestBody UsuarioRequest usuario) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUsuario(@PathVariable("id") Long idUsuario) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UsuarioRequest usuario) {
        return this.usuarioService.loginUsuario(usuario);
    }
}
