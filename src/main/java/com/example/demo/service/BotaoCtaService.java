package com.example.demo.service;

import com.example.demo.dto.BotaoCtaRequestDTO;
import com.example.demo.dto.BotaoCtaResponseDTO;
import com.example.demo.model.BotaoCta;
import com.example.demo.model.Pagina;
import com.example.demo.repository.BotaoCtaRepository;
import com.example.demo.repository.PaginaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BotaoCtaService {

    private final BotaoCtaRepository botaoCtaRepository;
    private final PaginaRepository paginaRepository;

    public BotaoCtaService(BotaoCtaRepository botaoCtaRepository, PaginaRepository paginaRepository) {
        this.botaoCtaRepository = botaoCtaRepository;
        this.paginaRepository = paginaRepository;
    }

    @Transactional
    public BotaoCtaResponseDTO criarBotaoCta(BotaoCtaRequestDTO dto) {
        Pagina pagina = paginaRepository.findById(dto.paginaId()).orElseThrow(() -> new RuntimeException("Página não encontrada"));
        BotaoCta b = new BotaoCta();
        b.setPagina(pagina);
        b.setTextoExibicao(dto.textoExibicao());
        b.setLinkDestino(dto.linkDestino());
        b = botaoCtaRepository.save(b);
        return new BotaoCtaResponseDTO(b.getId(), pagina.getId(), b.getTextoExibicao(), b.getLinkDestino());
    }

    public List<BotaoCtaResponseDTO> listarBotoesCtaPorPagina(Long paginaId) {
        return botaoCtaRepository.findByPaginaId(paginaId).stream()
                .map(b -> new BotaoCtaResponseDTO(b.getId(), paginaId, b.getTextoExibicao(), b.getLinkDestino()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletarBotaoCta(Long id) {
        botaoCtaRepository.deleteById(id);
    }
}
