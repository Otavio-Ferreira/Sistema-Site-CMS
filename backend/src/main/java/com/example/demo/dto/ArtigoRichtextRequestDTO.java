package com.example.demo.dto;

import java.time.LocalDate;

public record ArtigoRichtextRequestDTO(
    Long paginaId,
    String titulo,
    String conteudoHtml,
    LocalDate dataPublicacao
) {}
