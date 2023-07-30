package com.example.hospital.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioRequest {

    @NotNull(message = "O usuario não pode ser nulo")
    @NotBlank(message = "O usuario não pode ser em branco")
    @NotEmpty(message = "O usuario não pode vazio")
    private String usuario;

    @NotNull(message = "A senha não pode ser nula")
    @NotBlank(message = "A senha não pode ser em branco")
    @NotEmpty(message = "A senha não pode vazia")
    private String senha;

    @NotNull(message = "A dataNasicmento não pode ser nulo")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @NotNull(message = "O nome não pode ser nula")
    @NotBlank(message = "O nome não pode ser em branco")
    @NotEmpty(message = "O nome não pode vazia")
    private String nome;
}
