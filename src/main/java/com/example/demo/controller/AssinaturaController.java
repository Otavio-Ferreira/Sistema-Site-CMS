package com.example.demo.controller;

import com.example.demo.dto.AssinaturaRequestDTO;
import com.example.demo.dto.AssinaturaResponseDTO;
import com.example.demo.service.AssinaturaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assinaturas")
@CrossOrigin(origins = "*")
public class AssinaturaController {

    private final AssinaturaService assinaturaService;

    public AssinaturaController(AssinaturaService assinaturaService) {
        this.assinaturaService = assinaturaService;
    }

    @PostMapping
    public ResponseEntity<AssinaturaResponseDTO> criarAssinatura(@RequestBody AssinaturaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(assinaturaService.criarAssinatura(dto));
    }

    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<List<AssinaturaResponseDTO>> listarAssinaturasPorTenant(@PathVariable Long tenantId) {
        return ResponseEntity.ok(assinaturaService.listarAssinaturasPorTenant(tenantId));
    }
}
