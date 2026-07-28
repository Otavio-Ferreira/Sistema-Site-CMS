package com.example.demo.dto;

public record BotaoCtaRequestDTO(
    Long paginaId,
    String textoExibicao,
    String linkDestino
) {}
