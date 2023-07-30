package com.example.hospital.service;


import com.example.hospital.model.Usuario;
import com.example.hospital.repository.UsuarioRepository;
import com.example.hospital.request.UsuarioRequest;
import com.example.hospital.response.UsuarioResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public ResponseEntity criarUsuario(UsuarioRequest dadosUsuario) {
        try{

            Usuario usuario = Usuario.builder()
                    .usuario(dadosUsuario.getUsuario())
                    .senha(dadosUsuario.getSenha())
                    .nome(dadosUsuario.getNome())
                    .dataNascimento(dadosUsuario.getDataNascimento())
                    .build();

            this.usuarioRepository.save(usuario);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            UsuarioResponse response = UsuarioResponse.builder()
                    .nome(dadosUsuario.getUsuario())
                    .dataNascimento(dadosUsuario.getDataNascimento().format(formatter))
                    .build();

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao fazer login");
        }
    }

    public ResponseEntity verUsuarios() {
        try{
            List<Usuario> usuarios = (List<Usuario>) this.usuarioRepository.findAll();
            return ResponseEntity.ok().body(usuarios);
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar dos dados de usuarios");
        }
    }

    public ResponseEntity loginUsuario(UsuarioRequest dadosUsuario) {
        try {

            List<Usuario> usuarios = (List<Usuario>) this.usuarioRepository.findAll();
            Usuario usuarioEncontrado = null;

            for(Usuario usuario : usuarios){
                if(usuario.getUsuario().equals(dadosUsuario.getUsuario())){
                    usuarioEncontrado = usuario;
                }
            }

            if(usuarioEncontrado != null){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                return ResponseEntity.ok()
                        .body(UsuarioResponse.builder()
                                .nome(usuarioEncontrado.getNome())
                                .dataNascimento(usuarioEncontrado.getDataNascimento().format(formatter))
                                .build());
            } else {
                UsuarioResponse response = UsuarioResponse.builder().mensagem("Usuario nao existe na base de dados").build();
                return ResponseEntity.badRequest().body(response);
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao fazer login");
        }
    }
}
