package com.example.demo.controller;

import com.example.demo.dto.TenantRequestDTO;
import com.example.demo.dto.TenantResponseDTO;
import com.example.demo.service.TenantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tenants")
@CrossOrigin(origins = "*")
public class TenantController {

    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @PostMapping
    public ResponseEntity<TenantResponseDTO> cadastrar(@RequestBody TenantRequestDTO dto) {
        TenantResponseDTO response = tenantService.criarTenant(dto);
        // Retorna o status HTTP 201 (Created), que é o padrão correto para criações no REST
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}