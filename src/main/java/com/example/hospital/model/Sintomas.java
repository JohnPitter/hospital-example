package com.example.hospital.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "sintomas")
@Data
public class Sintomas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pergunta;
    private String resposta;
    private Integer peso;

    @ManyToMany(mappedBy = "sintomas")
    private List<Paciente> pacientes;
}
