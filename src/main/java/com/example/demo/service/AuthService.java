package com.example.demo.service;

import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.dto.LoginResponseDTO;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public LoginResponseDTO autenticar(LoginRequestDTO dto) {
        Usuario usuario = usuarioRepository.findByEmail(dto.email())
                .orElseThrow(() -> new RuntimeException("Usuário ou senha inválidos"));

        // Validação direta para desenvolvimento inicial
        if (!usuario.getSenhaHash().equals(dto.senha())) {
            throw new RuntimeException("Usuário ou senha inválidos");
        }

        String tokenFake = UUID.randomUUID().toString();

        return new LoginResponseDTO(
                usuario.getId(),
                usuario.getNomeCompleto(),
                usuario.getEmail(),
                tokenFake,
                usuario.getTenant().getId()
        );
    }
}