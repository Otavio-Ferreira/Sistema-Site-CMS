package com.example.demo.dto;

import java.time.LocalDate;

public record ArtigoRichtextResponseDTO(
    Long id,
    Long paginaId,
    String titulo,
    String conteudoHtml,
    LocalDate dataPublicacao
) {}
