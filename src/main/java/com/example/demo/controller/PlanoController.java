package com.example.demo.controller;

import com.example.demo.dto.PlanoRequestDTO;
import com.example.demo.dto.PlanoResponseDTO;
import com.example.demo.service.PlanoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planos")
@CrossOrigin(origins = "*")
public class PlanoController {

    private final PlanoService planoService;

    public PlanoController(PlanoService planoService) {
        this.planoService = planoService;
    }

    @PostMapping
    public ResponseEntity<PlanoResponseDTO> criarPlano(@RequestBody PlanoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(planoService.criarPlano(dto));
    }

    @GetMapping
    public ResponseEntity<List<PlanoResponseDTO>> listarPlanos() {
        return ResponseEntity.ok(planoService.listarPlanos());
    }
}
