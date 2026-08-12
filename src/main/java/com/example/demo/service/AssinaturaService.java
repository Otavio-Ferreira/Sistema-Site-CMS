package com.example.demo.service;

import com.example.demo.dto.AssinaturaRequestDTO;
import com.example.demo.dto.AssinaturaResponseDTO;
import com.example.demo.model.Assinatura;
import com.example.demo.model.Plano;
import com.example.demo.model.Tenant;
import com.example.demo.repository.AssinaturaRepository;
import com.example.demo.repository.PlanoRepository;
import com.example.demo.repository.TenantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssinaturaService {

    private final AssinaturaRepository assinaturaRepository;
    private final TenantRepository tenantRepository;
    private final PlanoRepository planoRepository;

    public AssinaturaService(AssinaturaRepository assinaturaRepository, TenantRepository tenantRepository, PlanoRepository planoRepository) {
        this.assinaturaRepository = assinaturaRepository;
        this.tenantRepository = tenantRepository;
        this.planoRepository = planoRepository;
    }

    @Transactional
    public AssinaturaResponseDTO criarAssinatura(AssinaturaRequestDTO dto) {
        Tenant tenant = tenantRepository.findById(dto.tenantId()).orElseThrow(() -> new RuntimeException("Tenant não encontrado"));
        Plano plano = planoRepository.findById(dto.planoId()).orElseThrow(() -> new RuntimeException("Plano não encontrado"));
        Assinatura a = new Assinatura();
        a.setTenant(tenant);
        a.setPlano(plano);
        a.setDataInicio(dto.dataInicio());
        a.setStatusPagamento(dto.statusPagamento());
        a = assinaturaRepository.save(a);
        return new AssinaturaResponseDTO(a.getId(), tenant.getId(), tenant.getNome(), plano.getId(), plano.getNomePlano(), a.getDataInicio(), a.getStatusPagamento());
    }

    public List<AssinaturaResponseDTO> listarAssinaturasPorTenant(Long tenantId) {
        return assinaturaRepository.findByTenantId(tenantId).stream()
                .map(a -> new AssinaturaResponseDTO(a.getId(), a.getTenant().getId(), a.getTenant().getNome(), a.getPlano().getId(), a.getPlano().getNomePlano(), a.getDataInicio(), a.getStatusPagamento()))
                .collect(Collectors.toList());
    }
}
