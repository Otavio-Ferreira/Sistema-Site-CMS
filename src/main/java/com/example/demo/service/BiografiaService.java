package com.example.demo.service;

import com.example.demo.dto.BiografiaRequestDTO;
import com.example.demo.dto.BiografiaResponseDTO;
import com.example.demo.model.Biografia;
import com.example.demo.model.Pagina;
import com.example.demo.repository.BiografiaRepository;
import com.example.demo.repository.PaginaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BiografiaService {

    private final BiografiaRepository biografiaRepository;
    private final PaginaRepository paginaRepository;

    public BiografiaService(BiografiaRepository biografiaRepository, PaginaRepository paginaRepository) {
        this.biografiaRepository = biografiaRepository;
        this.paginaRepository = paginaRepository;
    }

    @Transactional
    public BiografiaResponseDTO salvarBiografia(BiografiaRequestDTO dto) {
        Pagina pagina = paginaRepository.findById(dto.paginaId()).orElseThrow(() -> new RuntimeException("Página não encontrada"));
        Biografia bio = biografiaRepository.findByPaginaId(dto.paginaId()).orElse(new Biografia());
        bio.setPagina(pagina);
        bio.setConteudoTexto(dto.conteudoTexto());
        bio = biografiaRepository.save(bio);
        return new BiografiaResponseDTO(bio.getId(), pagina.getId(), bio.getConteudoTexto());
    }

    public BiografiaResponseDTO buscarBiografiaPorPagina(Long paginaId) {
        Biografia bio = biografiaRepository.findByPaginaId(paginaId).orElseThrow(() -> new RuntimeException("Biografia não encontrada"));
        return new BiografiaResponseDTO(bio.getId(), paginaId, bio.getConteudoTexto());
    }
}
