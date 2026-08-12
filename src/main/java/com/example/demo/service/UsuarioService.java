package com.example.demo.service;

import com.example.demo.dto.UsuarioRequestDTO;
import com.example.demo.dto.UsuarioResponseDTO;
import com.example.demo.model.Tenant;
import com.example.demo.model.Usuario;
import com.example.demo.repository.TenantRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final TenantRepository tenantRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, TenantRepository tenantRepository) {
        this.usuarioRepository = usuarioRepository;
        this.tenantRepository = tenantRepository;
    }

    @Transactional
    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO dto) {
        Tenant tenant = dto.tenantId() != null ? tenantRepository.findById(dto.tenantId()).orElse(null) : null;
        Usuario u = new Usuario();
        u.setNomeCompleto(dto.nomeCompleto());
        u.setEmail(dto.email());
        u.setSenhaHash(dto.senha());
        u.setAdmin(dto.isAdmin());
        u.setTenant(tenant);
        u = usuarioRepository.save(u);
        return new UsuarioResponseDTO(u.getId(), u.getNomeCompleto(), u.getEmail(), u.isAdmin(), tenant != null ? tenant.getId() : null);
    }

    public List<UsuarioResponseDTO> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(u -> new UsuarioResponseDTO(u.getId(), u.getNomeCompleto(), u.getEmail(), u.isAdmin(), u.getTenant() != null ? u.getTenant().getId() : null))
                .collect(Collectors.toList());
    }
}
