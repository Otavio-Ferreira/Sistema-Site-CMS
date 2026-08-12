package com.example.demo.service;

import com.example.demo.dto.AccordionRequestDTO;
import com.example.demo.dto.AccordionResponseDTO;
import com.example.demo.model.Accordion;
import com.example.demo.model.Pagina;
import com.example.demo.repository.AccordionRepository;
import com.example.demo.repository.PaginaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccordionService {

    private final AccordionRepository accordionRepository;
    private final PaginaRepository paginaRepository;

    public AccordionService(AccordionRepository accordionRepository, PaginaRepository paginaRepository) {
        this.accordionRepository = accordionRepository;
        this.paginaRepository = paginaRepository;
    }

    @Transactional
    public AccordionResponseDTO criarAccordion(AccordionRequestDTO dto) {
        Pagina pagina = paginaRepository.findById(dto.paginaId()).orElseThrow(() -> new RuntimeException("Página não encontrada"));
        Accordion a = new Accordion();
        a.setPagina(pagina);
        a.setPerguntaTitulo(dto.perguntaTitulo());
        a.setRespostaConteudo(dto.respostaConteudo());
        a = accordionRepository.save(a);
        return new AccordionResponseDTO(a.getId(), pagina.getId(), a.getPerguntaTitulo(), a.getRespostaConteudo());
    }

    public List<AccordionResponseDTO> listarAccordionsPorPagina(Long paginaId) {
        return accordionRepository.findByPaginaId(paginaId).stream()
                .map(a -> new AccordionResponseDTO(a.getId(), paginaId, a.getPerguntaTitulo(), a.getRespostaConteudo()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletarAccordion(Long id) {
        accordionRepository.deleteById(id);
    }
}
