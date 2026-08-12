package com.example.demo.controller;

import com.example.demo.dto.CardCtaRequestDTO;
import com.example.demo.dto.CardCtaResponseDTO;
import com.example.demo.service.CardCtaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards-cta")
@CrossOrigin(origins = "*")
public class CardCtaController {

    private final CardCtaService cardCtaService;

    public CardCtaController(CardCtaService cardCtaService) {
        this.cardCtaService = cardCtaService;
    }

    @PostMapping
    public ResponseEntity<CardCtaResponseDTO> criarCardCta(@RequestBody CardCtaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cardCtaService.criarCardCta(dto));
    }

    @GetMapping("/pagina/{paginaId}")
    public ResponseEntity<List<CardCtaResponseDTO>> listarCardsCtaPorPagina(@PathVariable Long paginaId) {
        return ResponseEntity.ok(cardCtaService.listarCardsCtaPorPagina(paginaId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCardCta(@PathVariable Long id) {
        cardCtaService.deletarCardCta(id);
        return ResponseEntity.noContent().build();
    }
}
