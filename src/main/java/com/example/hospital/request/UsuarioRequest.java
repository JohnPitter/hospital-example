package com.example.hospital.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

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
}
