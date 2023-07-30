package com.example.hospital.request;

import com.example.hospital.model.Sintomas;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PacienteRequest {
    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ser em branco")
    @NotEmpty(message = "O nome não pode vazio")
    private String nome;

    @NotNull(message = "A dataNasicmento não pode ser nulo")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @NotNull(message = "Os sintomas não podem ser nulos")
    private List<Sintomas> sintomas;
}
