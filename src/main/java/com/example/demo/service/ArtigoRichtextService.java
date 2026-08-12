package com.example.demo.service;

import com.example.demo.dto.ArtigoRichtextRequestDTO;
import com.example.demo.dto.ArtigoRichtextResponseDTO;
import com.example.demo.model.ArtigoRichtext;
import com.example.demo.model.Pagina;
import com.example.demo.repository.ArtigoRichtextRepository;
import com.example.demo.repository.PaginaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtigoRichtextService {

    private final ArtigoRichtextRepository artigoRichtextRepository;
    private final PaginaRepository paginaRepository;

    public ArtigoRichtextService(ArtigoRichtextRepository artigoRichtextRepository, PaginaRepository paginaRepository) {
        this.artigoRichtextRepository = artigoRichtextRepository;
        this.paginaRepository = paginaRepository;
    }

    @Transactional
    public ArtigoRichtextResponseDTO criarArtigo(ArtigoRichtextRequestDTO dto) {
        Pagina pagina = paginaRepository.findById(dto.paginaId()).orElseThrow(() -> new RuntimeException("Página não encontrada"));
        ArtigoRichtext a = new ArtigoRichtext();
        a.setPagina(pagina);
        a.setTitulo(dto.titulo());
        a.setConteudoHtml(dto.conteudoHtml());
        a.setDataPublicacao(dto.dataPublicacao() != null ? dto.dataPublicacao() : LocalDate.now());
        a = artigoRichtextRepository.save(a);
        return new ArtigoRichtextResponseDTO(a.getId(), pagina.getId(), a.getTitulo(), a.getConteudoHtml(), a.getDataPublicacao());
    }

    public List<ArtigoRichtextResponseDTO> listarArtigosPorPagina(Long paginaId) {
        return artigoRichtextRepository.findByPaginaId(paginaId).stream()
                .map(a -> new ArtigoRichtextResponseDTO(a.getId(), paginaId, a.getTitulo(), a.getConteudoHtml(), a.getDataPublicacao()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletarArtigo(Long id) {
        artigoRichtextRepository.deleteById(id);
    }
}
