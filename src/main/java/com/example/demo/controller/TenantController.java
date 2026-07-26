package com.example.demo.controller;

import com.example.demo.dto.TenantRequestDTO;
import com.example.demo.dto.TenantResponseDTO;
import com.example.demo.service.TenantService;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<TenantResponseDTO>> listarTodos() {
        return ResponseEntity.ok(tenantService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TenantResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tenantService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TenantResponseDTO> atualizar(@PathVariable Long id, @RequestBody TenantRequestDTO dto) {
        return ResponseEntity.ok(tenantService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        tenantService.deletar(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content (padrão para deletes com sucesso)
    }
}