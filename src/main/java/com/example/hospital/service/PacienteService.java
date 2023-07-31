package com.example.hospital.service;

import com.example.hospital.model.Paciente;
import com.example.hospital.model.Sintomas;
import com.example.hospital.repository.PacienteRepository;
import com.example.hospital.request.PacienteRequest;
import com.example.hospital.response.PacienteResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class PacienteService {

    private PacienteRepository pacienteRepository;

    public ResponseEntity cadastrarPaciente(PacienteRequest dadosPaciente) {

        try {
            Paciente paciente = Paciente.builder()
                    .nome(dadosPaciente.getNome())
                    .dataNascimento(dadosPaciente.getDataNascimento())
                    .dataCadastro(LocalDateTime.now())
                    .sintomas(dadosPaciente.getSintomas())
                    .pulseira(defineCorPulseira(dadosPaciente.getSintomas()))
                    .build();

            this.pacienteRepository.save(paciente);

            PacienteResponse response = PacienteResponse.builder()
                    .mensagem("Paciente cadastrado com sucesso.")
                    .nomePaciente(paciente.getNome())
                    .corPulseira(corEmIngles(paciente.getPulseira()))
                    .build();

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar os dados do paciente");
        }
    }

    public String defineCorPulseira(List<Sintomas> sintomas) {

        String corPulseira = "Azul";
        int pesoTotal = 0;
        int qtd = 0;

        for (Sintomas sintoma : sintomas) {

            if(sintoma.getResposta() != "Prefiro nao Responder"){
                pesoTotal += sintoma.getPeso();
                qtd++;
            }
        }

        int pesoPorPerguntas = pesoTotal / qtd;

        if (pesoPorPerguntas >= 9) {
            corPulseira = "Vermelha";
        } else if (pesoPorPerguntas >= 7 && pesoPorPerguntas < 9) {
            corPulseira = "Laranja";
        } else if (pesoPorPerguntas >= 5 && pesoPorPerguntas < 7) {
            corPulseira = "Amarelo";
        } else if (pesoPorPerguntas >= 3 && pesoPorPerguntas < 5) {
            corPulseira = "Verde";
        }

        return corPulseira;
    }

    private String corEmIngles(String corEmPortugues){
        List<String> coresPortugues = Arrays.asList("Vermelho", "Laranja", "Amarelo", "Verde", "Azul");
        List<String> coresIngles = Arrays.asList("red", "orange", "yellow", "green", "blue");

        String corEmIngles = "";

        for (int i = 0; i < coresPortugues.size(); i++) {
            if (coresPortugues.get(i).equals(corEmPortugues)) {
                corEmIngles = coresIngles.get(i);
                break;
            }
        }

        return corEmIngles;
    }

    public ResponseEntity verPacientes() {
        try {
            List<Paciente> pacientes = (List<Paciente>) pacienteRepository.findAll();
            return ResponseEntity.ok(pacientes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar os dados dos pacientes");
        }
    }
}
