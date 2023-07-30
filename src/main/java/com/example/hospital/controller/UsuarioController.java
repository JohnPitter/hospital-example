package com.example.hospital.controller;

import com.example.hospital.request.UsuarioRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @GetMapping
    public ResponseEntity getUsuarios() {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity createUsuario(@Valid @RequestBody UsuarioRequest usuario) {
        return ResponseEntity.ok().build();
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
        return ResponseEntity.ok().build();
    }
}
