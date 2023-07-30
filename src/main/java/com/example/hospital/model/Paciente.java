package com.example.hospital.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private LocalDate dataNascimento;
    private LocalDateTime dataCadastro;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "paciente_sintomas",
            joinColumns = @JoinColumn(name = "paciente_id"),
            inverseJoinColumns = @JoinColumn(name = "sintomas_id"))
    private List<Sintomas> sintomas;

    //Vermelha(Emergência), Laranja(Muito Urgente), Amarelo(Urgente), Verde(Pouco Urgente), Azul(Não Urgente)
    private String pulseira;

}
