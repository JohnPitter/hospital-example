package com.example.hospital.controller;

import com.example.hospital.request.PacienteRequest;
import com.example.hospital.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @GetMapping
    public ResponseEntity getPacientes() {
        return this.pacienteService.verPacientes();
    }

    @PostMapping
    public ResponseEntity createPaciente(@Valid @RequestBody PacienteRequest paciente) {
        return this.pacienteService.cadastrarPaciente(paciente);
    }

    @PutMapping("/{id}")
    public ResponseEntity updatePaciente(@PathVariable("id") Long idPaciente, @Valid @RequestBody PacienteRequest paciente) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePaciente(@PathVariable("id") Long idPaciente) {
        return ResponseEntity.ok().build();
    }
}
