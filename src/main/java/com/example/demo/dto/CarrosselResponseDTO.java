package com.example.demo.dto;

import java.util.List;

public record CarrosselResponseDTO(
    Long id,
    Long paginaId,
    List<ImagemCarrosselResponseDTO> imagens
) {}
