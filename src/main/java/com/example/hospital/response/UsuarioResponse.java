package com.example.hospital.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioResponse {
    private String nome;
    private String dataNascimento;
}
