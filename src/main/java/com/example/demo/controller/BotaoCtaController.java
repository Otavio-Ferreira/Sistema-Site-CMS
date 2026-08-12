package com.example.demo.controller;

import com.example.demo.dto.BotaoCtaRequestDTO;
import com.example.demo.dto.BotaoCtaResponseDTO;
import com.example.demo.service.BotaoCtaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/botoes-cta")
@CrossOrigin(origins = "*")
public class BotaoCtaController {

    private final BotaoCtaService botaoCtaService;

    public BotaoCtaController(BotaoCtaService botaoCtaService) {
        this.botaoCtaService = botaoCtaService;
    }

    @PostMapping
    public ResponseEntity<BotaoCtaResponseDTO> criarBotaoCta(@RequestBody BotaoCtaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(botaoCtaService.criarBotaoCta(dto));
    }

    @GetMapping("/pagina/{paginaId}")
    public ResponseEntity<List<BotaoCtaResponseDTO>> listarBotoesCtaPorPagina(@PathVariable Long paginaId) {
        return ResponseEntity.ok(botaoCtaService.listarBotoesCtaPorPagina(paginaId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarBotaoCta(@PathVariable Long id) {
        botaoCtaService.deletarBotaoCta(id);
        return ResponseEntity.noContent().build();
    }
}
