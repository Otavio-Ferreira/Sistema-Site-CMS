package com.example.demo.controller;

import com.example.demo.dto.ArtigoRichtextRequestDTO;
import com.example.demo.dto.ArtigoRichtextResponseDTO;
import com.example.demo.service.ArtigoRichtextService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artigos")
@CrossOrigin(origins = "*")
public class ArtigoRichtextController {

    private final ArtigoRichtextService artigoRichtextService;

    public ArtigoRichtextController(ArtigoRichtextService artigoRichtextService) {
        this.artigoRichtextService = artigoRichtextService;
    }

    @PostMapping
    public ResponseEntity<ArtigoRichtextResponseDTO> criarArtigo(@RequestBody ArtigoRichtextRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(artigoRichtextService.criarArtigo(dto));
    }

    @GetMapping("/pagina/{paginaId}")
    public ResponseEntity<List<ArtigoRichtextResponseDTO>> listarArtigosPorPagina(@PathVariable Long paginaId) {
        return ResponseEntity.ok(artigoRichtextService.listarArtigosPorPagina(paginaId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarArtigo(@PathVariable Long id) {
        artigoRichtextService.deletarArtigo(id);
        return ResponseEntity.noContent().build();
    }
}
