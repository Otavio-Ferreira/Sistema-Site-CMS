package com.example.demo.service;

import com.example.demo.dto.CardCtaRequestDTO;
import com.example.demo.dto.CardCtaResponseDTO;
import com.example.demo.model.CardCta;
import com.example.demo.model.Pagina;
import com.example.demo.repository.CardCtaRepository;
import com.example.demo.repository.PaginaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardCtaService {

    private final CardCtaRepository cardCtaRepository;
    private final PaginaRepository paginaRepository;

    public CardCtaService(CardCtaRepository cardCtaRepository, PaginaRepository paginaRepository) {
        this.cardCtaRepository = cardCtaRepository;
        this.paginaRepository = paginaRepository;
    }

    @Transactional
    public CardCtaResponseDTO criarCardCta(CardCtaRequestDTO dto) {
        Pagina pagina = paginaRepository.findById(dto.paginaId()).orElseThrow(() -> new RuntimeException("Página não encontrada"));
        CardCta c = new CardCta();
        c.setPagina(pagina);
        c.setUrlImagem(dto.urlImagem());
        c.setTextoDestaque(dto.textoDestaque());
        c.setLinkDestino(dto.linkDestino());
        c = cardCtaRepository.save(c);
        return new CardCtaResponseDTO(c.getId(), pagina.getId(), c.getUrlImagem(), c.getTextoDestaque(), c.getLinkDestino());
    }

    public List<CardCtaResponseDTO> listarCardsCtaPorPagina(Long paginaId) {
        return cardCtaRepository.findByPaginaId(paginaId).stream()
                .map(c -> new CardCtaResponseDTO(c.getId(), paginaId, c.getUrlImagem(), c.getTextoDestaque(), c.getLinkDestino()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletarCardCta(Long id) {
        cardCtaRepository.deleteById(id);
    }
}
