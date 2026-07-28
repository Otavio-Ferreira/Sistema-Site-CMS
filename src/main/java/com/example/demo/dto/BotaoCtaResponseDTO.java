package com.example.demo.dto;

public record BotaoCtaResponseDTO(
    Long id,
    Long paginaId,
    String textoExibicao,
    String linkDestino
) {}
