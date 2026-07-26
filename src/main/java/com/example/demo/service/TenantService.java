package com.example.demo.service;

import com.example.demo.dto.TenantRequestDTO;
import com.example.demo.dto.TenantResponseDTO;
import com.example.demo.model.Tenant;
import com.example.demo.model.Usuario;
import com.example.demo.repository.TenantRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TenantService {

    private final TenantRepository tenantRepository;
    private final UsuarioRepository usuarioRepository;

    public TenantService(TenantRepository tenantRepository, UsuarioRepository usuarioRepository) {
        this.tenantRepository = tenantRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional 
    public TenantResponseDTO criarTenant(TenantRequestDTO dto) {
        
        if (usuarioRepository.findByEmail(dto.email()).isPresent()) {
            throw new RuntimeException("Este e-mail já está cadastrado em nosso sistema.");
        }

        Tenant tenant = new Tenant();
        tenant.setNome(dto.nome());
        tenant = tenantRepository.save(tenant);

        Usuario usuario = new Usuario();
        usuario.setNomeCompleto(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenhaHash(dto.senha()); 
        usuario.setAdmin(true);
        usuario.setTenant(tenant);
        usuarioRepository.save(usuario);

        return new TenantResponseDTO(tenant.getId(), tenant.getNome(), usuario.getEmail());
    }
}