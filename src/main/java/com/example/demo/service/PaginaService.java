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
import java.util.stream.Collectors;

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
        Tenant tenant = tenantRepository.findById(dto.tenantId())
                .orElseThrow(() -> new RuntimeException("Tenant não encontrado"));
        Pagina pagina = new Pagina();
        pagina.setTenant(tenant);
        pagina.setUrlPublica(dto.urlPublica());
        pagina.setTituloPagina(dto.tituloPagina());
        pagina.setDataCriacao(LocalDate.now());
        pagina = paginaRepository.save(pagina);
        return new PaginaResponseDTO(pagina.getId(), tenant.getId(), pagina.getUrlPublica(), pagina.getTituloPagina(), pagina.getDataCriacao());
    }

    public List<PaginaResponseDTO> listarPaginasPorTenant(Long tenantId) {
        return paginaRepository.findByTenantId(tenantId).stream()
                .map(p -> new PaginaResponseDTO(p.getId(), p.getTenant().getId(), p.getUrlPublica(), p.getTituloPagina(), p.getDataCriacao()))
                .collect(Collectors.toList());
    }

    public PaginaResponseDTO buscarPaginaPorId(Long id) {
        Pagina p = paginaRepository.findById(id).orElseThrow(() -> new RuntimeException("Página não encontrada"));
        return new PaginaResponseDTO(p.getId(), p.getTenant().getId(), p.getUrlPublica(), p.getTituloPagina(), p.getDataCriacao());
    }

    @Transactional
    public void deletarPagina(Long id) {
        paginaRepository.deleteById(id);
    }
}
