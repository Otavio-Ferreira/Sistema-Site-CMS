package com.example.demo.controller;

import com.example.demo.dto.PaginaRequestDTO;
import com.example.demo.dto.PaginaResponseDTO;
import com.example.demo.service.PaginaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paginas")
@CrossOrigin(origins = "*")
public class PaginaController {

    private final PaginaService paginaService;

    public PaginaController(PaginaService paginaService) {
        this.paginaService = paginaService;
    }

    @PostMapping
    public ResponseEntity<PaginaResponseDTO> criarPagina(@RequestBody PaginaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paginaService.criarPagina(dto));
    }

    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<List<PaginaResponseDTO>> listarPaginasPorTenant(@PathVariable Long tenantId) {
        return ResponseEntity.ok(paginaService.listarPaginasPorTenant(tenantId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaginaResponseDTO> buscarPaginaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(paginaService.buscarPaginaPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPagina(@PathVariable Long id) {
        paginaService.deletarPagina(id);
        return ResponseEntity.noContent().build();
    }
}
