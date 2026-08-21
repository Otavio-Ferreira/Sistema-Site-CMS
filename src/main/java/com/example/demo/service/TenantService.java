package com.example.demo.service;

import com.example.demo.dto.TenantRequestDTO;
import com.example.demo.dto.TenantResponseDTO;
import com.example.demo.model.Assinatura;
import com.example.demo.model.Tenant;
import com.example.demo.model.Usuario;
import com.example.demo.repository.AssinaturaRepository;
import com.example.demo.repository.TenantRepository;
import com.example.demo.repository.UsuarioRepository;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TenantService {

    private final TenantRepository tenantRepository;
    private final UsuarioRepository usuarioRepository;
    private final AssinaturaRepository assinaturaRepository;
    private final PasswordEncoder passwordEncoder;

    public TenantService(TenantRepository tenantRepository, UsuarioRepository usuarioRepository,
                         AssinaturaRepository assinaturaRepository, PasswordEncoder passwordEncoder) {
        this.tenantRepository = tenantRepository;
        this.usuarioRepository = usuarioRepository;
        this.assinaturaRepository = assinaturaRepository;
        this.passwordEncoder = passwordEncoder;
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
        usuario.setSenhaHash(passwordEncoder.encode(dto.senha()));
        usuario.setAdmin(false);
        usuario.setTenant(tenant);
        usuarioRepository.save(usuario);

        return new TenantResponseDTO(tenant.getId(), tenant.getNome(), usuario.getEmail());
    }

    // Buscar todos os inquilinos
    public List<TenantResponseDTO> listarTodos() {
        return tenantRepository.findAll().stream().map(tenant -> new TenantResponseDTO(
            tenant.getId(), 
            tenant.getNome(), 
            "Email oculto na listagem")).toList(); // Simplificação para a listagem
    }

    // Buscar um inquilino específico pelo ID
    public TenantResponseDTO buscarPorId(Long id) {
        Tenant tenant = tenantRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Inquilino não encontrado com o ID: " + id));

        return new TenantResponseDTO(tenant.getId(), tenant.getNome(), "Email confidencial");
    }

    // Atualizar os dados do inquilino
    @Transactional
    public TenantResponseDTO atualizar(Long id, TenantRequestDTO dto) {
        Tenant tenant = tenantRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Inquilino não encontrado com o ID: " + id));

        tenant.setNome(dto.nome());
        tenantRepository.save(tenant);

        return new TenantResponseDTO(tenant.getId(), tenant.getNome(), dto.email());
    }

    // Deletar um inquilino
    @Transactional
    public void deletar(Long id) {
        Tenant tenant = tenantRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Inquilino não encontrado com o ID: " + id));

        assinaturaRepository.findByTenantId(id).forEach(assinaturaRepository::delete);
        tenantRepository.delete(tenant);
    }
}