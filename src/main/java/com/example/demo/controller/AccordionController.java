package com.example.demo.controller;

import com.example.demo.dto.AccordionRequestDTO;
import com.example.demo.dto.AccordionResponseDTO;
import com.example.demo.service.AccordionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accordions")
@CrossOrigin(origins = "*")
public class AccordionController {

    private final AccordionService accordionService;

    public AccordionController(AccordionService accordionService) {
        this.accordionService = accordionService;
    }

    @PostMapping
    public ResponseEntity<AccordionResponseDTO> criarAccordion(@RequestBody AccordionRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accordionService.criarAccordion(dto));
    }

    @GetMapping("/pagina/{paginaId}")
    public ResponseEntity<List<AccordionResponseDTO>> listarAccordionsPorPagina(@PathVariable Long paginaId) {
        return ResponseEntity.ok(accordionService.listarAccordionsPorPagina(paginaId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAccordion(@PathVariable Long id) {
        accordionService.deletarAccordion(id);
        return ResponseEntity.noContent().build();
    }
}
