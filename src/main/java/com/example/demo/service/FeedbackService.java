package com.example.demo.service;

import com.example.demo.dto.FeedbackRequestDTO;
import com.example.demo.dto.FeedbackResponseDTO;
import com.example.demo.model.Feedback;
import com.example.demo.model.Pagina;
import com.example.demo.repository.FeedbackRepository;
import com.example.demo.repository.PaginaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final PaginaRepository paginaRepository;

    public FeedbackService(FeedbackRepository feedbackRepository, PaginaRepository paginaRepository) {
        this.feedbackRepository = feedbackRepository;
        this.paginaRepository = paginaRepository;
    }

    @Transactional
    public FeedbackResponseDTO criarFeedback(FeedbackRequestDTO dto) {
        Pagina pagina = paginaRepository.findById(dto.paginaId()).orElseThrow(() -> new RuntimeException("Página não encontrada"));
        Feedback f = new Feedback();
        f.setPagina(pagina);
        f.setNomeCliente(dto.nomeCliente());
        f.setTextoAvaliacao(dto.textoAvaliacao());
        f.setDataFeedback(dto.dataFeedback() != null ? dto.dataFeedback() : LocalDate.now());
        f = feedbackRepository.save(f);
        return new FeedbackResponseDTO(f.getId(), pagina.getId(), f.getNomeCliente(), f.getTextoAvaliacao(), f.getDataFeedback());
    }

    public List<FeedbackResponseDTO> listarFeedbacksPorPagina(Long paginaId) {
        return feedbackRepository.findByPaginaId(paginaId).stream()
                .map(f -> new FeedbackResponseDTO(f.getId(), paginaId, f.getNomeCliente(), f.getTextoAvaliacao(), f.getDataFeedback()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletarFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}
