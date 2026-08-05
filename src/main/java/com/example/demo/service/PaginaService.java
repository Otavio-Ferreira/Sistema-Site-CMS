package com.example.demo.service;

import com.example.demo.dto.PaginaRequestDTO;
import com.example.demo.dto.PaginaResponseDTO;
import com.example.demo.model.Pagina;
import com.example.demo.model.Tenant;
import com.example.demo.repository.PaginaRepository;
import com.example.demo.repository.TenantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class PaginaService {

    private final PaginaRepository paginaRepository;
    private final TenantRepository tenantRepository;

    public PaginaService(PaginaRepository paginaRepository, TenantRepository tenantRepository) {
        this.paginaRepository = paginaRepository;
        this.tenantRepository = tenantRepository;
    }

    @Transactional
    public PaginaResponseDTO criarPagina(PaginaRequestDTO dto) {
        Tenant tenant = tenantRepository.findById(dto.tenantId()).orElseThrow(() -> new RuntimeException("Cliente (Tenant) não encontrado."));

        if (paginaRepository.findByUrlPublica(dto.urlPublica()).isPresent()) {
            throw new RuntimeException("Esta URL já está em uso. Por favor, escolhe outra.");
        }

        Pagina pagina = new Pagina();
        pagina.setTituloPagina(dto.tituloPagina());
        pagina.setUrlPublica(dto.urlPublica());
        pagina.setTenant(tenant);
        pagina.setDataCriacao(LocalDate.now());

        pagina = paginaRepository.save(pagina);
        return new PaginaResponseDTO(
            pagina.getId(),
            tenant.getId(),
            pagina.getUrlPublica(),
            pagina.getTituloPagina(),
            pagina.getDataCriacao());
    }

    public List<PaginaResponseDTO> listarPorTenant(Long tenantId) {
        return paginaRepository.findByTenantId(tenantId).stream().map(p -> new PaginaResponseDTO(
            p.getId(),
            p.getTenant().getId(),
            p.getUrlPublica(),
            p.getTituloPagina(),
            p.getDataCriacao())).toList();
    }

    @Transactional
    public void eliminarPagina(Long id, Long tenantId) {
        // Verificar que um cliente não consiga apagar a página de outro
        Pagina pagina = paginaRepository.findById(id).orElseThrow(() -> new RuntimeException("Página não encontrada."));
        
        if (!pagina.getTenant().getId().equals(tenantId)) {
            throw new RuntimeException("Você não possui permissão para excluir essa página.");
        }
        
        paginaRepository.delete(pagina);
    }
}
