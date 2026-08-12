package com.example.demo.controller;

import com.example.demo.dto.FeedbackRequestDTO;
import com.example.demo.dto.FeedbackResponseDTO;
import com.example.demo.service.FeedbackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
@CrossOrigin(origins = "*")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public ResponseEntity<FeedbackResponseDTO> criarFeedback(@RequestBody FeedbackRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(feedbackService.criarFeedback(dto));
    }

    @GetMapping("/pagina/{paginaId}")
    public ResponseEntity<List<FeedbackResponseDTO>> listarFeedbacksPorPagina(@PathVariable Long paginaId) {
        return ResponseEntity.ok(feedbackService.listarFeedbacksPorPagina(paginaId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFeedback(@PathVariable Long id) {
        feedbackService.deletarFeedback(id);
        return ResponseEntity.noContent().build();
    }
}
