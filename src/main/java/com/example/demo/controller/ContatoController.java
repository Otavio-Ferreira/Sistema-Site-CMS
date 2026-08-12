package com.example.demo.controller;

import com.example.demo.dto.ContatoRequestDTO;
import com.example.demo.dto.ContatoResponseDTO;
import com.example.demo.service.ContatoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contatos")
@CrossOrigin(origins = "*")
public class ContatoController {

    private final ContatoService contatoService;

    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @PostMapping
    public ResponseEntity<ContatoResponseDTO> criarContato(@RequestBody ContatoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contatoService.criarContato(dto));
    }

    @GetMapping("/pagina/{paginaId}")
    public ResponseEntity<List<ContatoResponseDTO>> listarContatosPorPagina(@PathVariable Long paginaId) {
        return ResponseEntity.ok(contatoService.listarContatosPorPagina(paginaId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarContato(@PathVariable Long id) {
        contatoService.deletarContato(id);
        return ResponseEntity.noContent().build();
    }
}
