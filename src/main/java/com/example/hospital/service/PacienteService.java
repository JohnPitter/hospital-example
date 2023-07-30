package com.example.hospital.service;

import com.example.hospital.model.Paciente;
import com.example.hospital.model.Sintomas;
import com.example.hospital.repository.PacienteRepository;
import com.example.hospital.repository.SintomasRepository;
import com.example.hospital.request.PacienteRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PacienteService {

    private PacienteRepository pacienteRepository;
    private SintomasRepository sintomasRepository;

    public ResponseEntity cadastrarPaciente(PacienteRequest dadosPaciente) {

        try {
            List<Sintomas> sintomasPersistidos = new ArrayList<>();

            for (Sintomas sintoma : dadosPaciente.getSintomas()) {
                sintomasPersistidos.add(sintomasRepository.save(sintoma));
            }

            Paciente paciente = Paciente.builder()
                    .nome(dadosPaciente.getNome())
                    .dataNascimento(dadosPaciente.getDataNascimento())
                    .dataCadastro(LocalDateTime.now())
                    .sintomas(sintomasPersistidos)
                    .pulseira(defineCorPulseira(sintomasPersistidos))
                    .build();

            this.pacienteRepository.save(paciente);

            return ResponseEntity.ok().body("Paciente cadastrado com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public String defineCorPulseira(List<Sintomas> sintomas) {

        String corPulseira = "Azul";
        int pesoTotal = 0;

        for (Sintomas sintoma : sintomas) {
            pesoTotal += sintoma.getPeso();
        }

        int pesoPorPerguntas = pesoTotal / sintomas.size();

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

    public ResponseEntity verPacientes() {

        try {
            List<Paciente> pacientes = (List<Paciente>) this.pacienteRepository.findAll();
            ResponseEntity.ok().body(pacientes);
        } catch (Exception e) {
            ResponseEntity.ok().body(e.getMessage());
        }

        return ResponseEntity.ok().build();
    }
}
