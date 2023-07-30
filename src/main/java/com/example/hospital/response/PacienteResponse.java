package com.example.hospital.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PacienteResponse {
    private String mensagem;
    private String nomePaciente;
    private String corPulseira;
}
