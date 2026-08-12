package com.example.demo.controller;

import com.example.demo.dto.BiografiaRequestDTO;
import com.example.demo.dto.BiografiaResponseDTO;
import com.example.demo.service.BiografiaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/biografias")
@CrossOrigin(origins = "*")
public class BiografiaController {

    private final BiografiaService biografiaService;

    public BiografiaController(BiografiaService biografiaService) {
        this.biografiaService = biografiaService;
    }

    @PostMapping
    public ResponseEntity<BiografiaResponseDTO> salvarBiografia(@RequestBody BiografiaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(biografiaService.salvarBiografia(dto));
    }

    @GetMapping("/pagina/{paginaId}")
    public ResponseEntity<BiografiaResponseDTO> buscarBiografiaPorPagina(@PathVariable Long paginaId) {
        return ResponseEntity.ok(biografiaService.buscarBiografiaPorPagina(paginaId));
    }
}
