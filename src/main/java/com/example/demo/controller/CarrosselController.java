package com.example.demo.controller;

import com.example.demo.dto.CarrosselRequestDTO;
import com.example.demo.dto.CarrosselResponseDTO;
import com.example.demo.dto.ImagemCarrosselRequestDTO;
import com.example.demo.dto.ImagemCarrosselResponseDTO;
import com.example.demo.service.CarrosselService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CarrosselController {

    private final CarrosselService carrosselService;

    public CarrosselController(CarrosselService carrosselService) {
        this.carrosselService = carrosselService;
    }

    @PostMapping("/carrosseis")
    public ResponseEntity<CarrosselResponseDTO> criarCarrossel(@RequestBody CarrosselRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carrosselService.criarCarrossel(dto));
    }

    @GetMapping("/carrosseis/pagina/{paginaId}")
    public ResponseEntity<List<CarrosselResponseDTO>> listarCarrosseisPorPagina(@PathVariable Long paginaId) {
        return ResponseEntity.ok(carrosselService.listarCarrosseisPorPagina(paginaId));
    }

    @DeleteMapping("/carrosseis/{id}")
    public ResponseEntity<Void> deletarCarrossel(@PathVariable Long id) {
        carrosselService.deletarCarrossel(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/imagens-carrossel")
    public ResponseEntity<ImagemCarrosselResponseDTO> adicionarImagemCarrossel(@RequestBody ImagemCarrosselRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carrosselService.adicionarImagemCarrossel(dto));
    }

    @DeleteMapping("/imagens-carrossel/{id}")
    public ResponseEntity<Void> deletarImagemCarrossel(@PathVariable Long id) {
        carrosselService.deletarImagemCarrossel(id);
        return ResponseEntity.noContent().build();
    }
}
